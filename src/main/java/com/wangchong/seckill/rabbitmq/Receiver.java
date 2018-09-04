package com.wangchong.seckill.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.wangchong.seckill.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 15:05 2018/8/31
 */
@Service
public class Receiver {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues=RabbitmqConfig.queue)
    public void process(String msg)  {
           SeckillMessage bean = JSON.parseObject(msg,SeckillMessage.class);
           Long userId= bean.getUserId();
           Long goodsId = bean.getGoodsId();
           int  n =orderService.getSeckillOrder(goodsId,userId);
           if(n > 0) return;
           orderService.insertOrder(bean.getUserId(),bean.getGoodsId());



    }
}
