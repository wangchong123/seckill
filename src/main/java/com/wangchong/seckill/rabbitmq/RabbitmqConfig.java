package com.wangchong.seckill.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 14:56 2018/8/31
 */
@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue queue(){
        return new Queue("hello");
    }
}
