package com.wangchong.seckill.controller;

import com.wangchong.seckill.anno.LoginCheck;
import com.wangchong.seckill.entity.SeckillGoods;
import com.wangchong.seckill.entity.SeckillOrder;
import com.wangchong.seckill.entity.User;
import com.wangchong.seckill.rabbitmq.SeckillMessage;
import com.wangchong.seckill.rabbitmq.Sender;
import com.wangchong.seckill.service.GoodsService;
import com.wangchong.seckill.service.OrderService;
import com.wangchong.seckill.service.SeckillService;
import com.wangchong.seckill.util.Result;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 15:21 2018/9/4
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController  implements InitializingBean {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private Sender sender;

    private Map<Long,Boolean>  loadOverMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        List<SeckillGoods> list = seckillService.list();
        if(list != null){
            for (SeckillGoods s : list) {
                redisTemplate.opsForValue().set("_kc" + s.getGoodsId(),s.getStock());
                loadOverMap.put(s.getGoodsId(),false);
            }
        }
    }


    @ResponseBody
    @LoginCheck
    @RequestMapping("/seckill")
    public Result seckill(HttpServletRequest request,Long goodsId){
        User user = (User) request.getSession().getAttribute("user");
        String path = seckillService.getPath(user.getId(),goodsId);
        return Result.success(path);
    }


    @ResponseBody
    @LoginCheck
    @RequestMapping("/doSeckill/{path}")
    public Result doSeckill(HttpServletRequest request, Long goodsId, @PathVariable("path") String path){
        User user = (User) request.getSession().getAttribute("user");
        boolean checkPath = seckillService.checkPath(path,user.getId(),goodsId);
        if(!checkPath){
            return Result.error();
        }
        Boolean loadOver = loadOverMap.get(goodsId);
        if(loadOver){//结束
            return Result.error();
        }
        //预见库存
        long stock = redisTemplate.opsForValue().increment("_kc" + goodsId,(int)-1);
        System.out.println("---------------" + stock);
        if(stock < 0){
            loadOverMap.put(goodsId,true);
            return Result.error();
        }

        //判断是否秒杀到
        SeckillOrder n = orderService.getSeckillOrder(goodsId,user.getId());
        if(!(n == null)){
            return Result.error();
        }

        //入队
        SeckillMessage message = new SeckillMessage();
        message.setGoodsId(goodsId);
        message.setUserId(user.getId());
        sender.send(message);

        //返回排队中
        return Result.success(0);
    }

    @ResponseBody
    @LoginCheck
    @RequestMapping("/getSeckillResult")
    public Result getSeckillResult(HttpServletRequest request,Long goodsId){
        User user = (User) request.getSession().getAttribute("user");
        Long  n =seckillService.getSeckillResult(user.getId(),goodsId);
        return Result.success(n);
    }


}
