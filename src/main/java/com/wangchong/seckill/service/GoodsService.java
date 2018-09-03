package com.wangchong.seckill.service;

import com.wangchong.seckill.dao.GoodsDao;
import com.wangchong.seckill.entity.Goods;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 10:18 2018/8/31
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<Goods> list(){
        //redisTemplate.opsForValue().set("test","test");

       // System.out.println(redisTemplate.opsForValue().get("test"));
        return goodsDao.list();
    }


}
