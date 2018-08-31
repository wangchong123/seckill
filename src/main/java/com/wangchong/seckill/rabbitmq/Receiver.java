package com.wangchong.seckill.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 15:05 2018/8/31
 */
@Service
public class Receiver {

    @RabbitListener(queues="hello")
    public void process(String msg)  {
            System.out.println("收到---" + msg);



    }
}
