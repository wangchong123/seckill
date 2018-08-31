package com.wangchong.seckill.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 15:03 2018/8/31
 */
@Service
public class Sender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send(String msg){
        rabbitmqTemplate.convertAndSend("hello",msg);
        System.out.println("发送消息----" + msg);
    }
}
