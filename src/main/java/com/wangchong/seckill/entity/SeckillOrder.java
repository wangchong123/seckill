package com.wangchong.seckill.entity;

import lombok.Data;

/**
 * @Author: wangchong
 * @Description 秒杀订单表
 * @Date : Created in 16:01 2018/8/31
 */
@Data
public class SeckillOrder {

    private Long id;

    private Long orderId;

    private Long userId;
}
