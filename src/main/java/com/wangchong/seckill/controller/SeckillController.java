package com.wangchong.seckill.controller;

import com.wangchong.seckill.anno.LoginCheck;
import com.wangchong.seckill.entity.SeckillGoods;
import com.wangchong.seckill.entity.User;
import com.wangchong.seckill.service.GoodsService;
import com.wangchong.seckill.service.OrderService;
import com.wangchong.seckill.service.SeckillService;
import com.wangchong.seckill.util.Result;
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
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    private Map<Long,Boolean>  loadOverMap = new HashMap<>();

    public void goodsInit(){
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
    @RequestMapping("/seckill/{path}")
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
        long stock = redisTemplate.opsForValue().increment("_kc" + goodsId,-1);
        if(stock < 0){
            loadOverMap.put(goodsId,true);
            return Result.error();
        }

        //判断是否秒杀到
        int n = orderService.getSeckillOrder(goodsId,user.getId());
        if(n > 0){
            return Result.error();
        }


        return Result.success();
    }


}
