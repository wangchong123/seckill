package com.wangchong.seckill.rabbitmq;


import lombok.Data;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 17:23 2018/9/4
 */
@Data
public class SeckillMessage {

    private Long userId;

    private Long goodsId;



}
