package com.wangchong.seckill.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.wangchong.seckill.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
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

    public void send(Object message){
        String msg = JSON.toJSONString(message);
        rabbitmqTemplate.convertAndSend(RabbitmqConfig.queue,msg);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1l);
        user.setUsername("sw哇");
        String msg = JSON.toJSONString(user);
        System.out.println(user);
    }
}
