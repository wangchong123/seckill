package com.wangchong.seckill.service;

import com.wangchong.seckill.dao.SeckillGoodsDao;
import com.wangchong.seckill.entity.Order;
import com.wangchong.seckill.entity.SeckillGoods;
import com.wangchong.seckill.entity.SeckillOrder;
import com.wangchong.seckill.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 15:29 2018/9/4
 */
@Service
public class SeckillService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;

    @Autowired
    private OrderService orderService;

    public String getPath(Long userId,Long goodsId){
        if(userId == null || goodsId == null){
            return null;
        }
        String key = userId + "_" + goodsId + "mp";
        String value = UUIDUtil.uuid();
        redisTemplate.opsForValue().set(key, value, 60,TimeUnit.SECONDS);
        return value;
    }

    public boolean checkPath(String path,Long userId,Long goodsId){
        if(userId == null || goodsId == null || path == null){
            return false;
        }
        String key = userId + "_" + goodsId + "mp";
        String value = (String) redisTemplate.opsForValue().get(key);
        return value.equals(path);
    }

    public List<SeckillGoods> list(){
        return seckillGoodsDao.list();
    }

    @Transactional
    public Order doSeckill(Long userId, Long goodsId){

        boolean succ = orderService.reduceScock(goodsId);
        if(succ){
            return orderService.insertOrder(userId,goodsId);
        }else{
            setGoodsOver(goodsId);
        }
        return null;

    }

    public Long getSeckillResult(Long userId,Long goodsId){
        SeckillOrder n =orderService.getSeckillOrder(goodsId,userId);
        if(n != null )
            return n.getOrderId(); //秒杀成功
        else{
            boolean over = getGoodsOver(goodsId);
            if(over){
                return -1L;
            }else{
                return 0L;
            }
        }

    }

    private void setGoodsOver(Long goodsId){
        redisTemplate.opsForValue().set("_sckc" + goodsId,true,30,TimeUnit.SECONDS);
    }

    private boolean getGoodsOver(Long goodsId){
        return redisTemplate.hasKey("_sckc" + goodsId);
    }
}
