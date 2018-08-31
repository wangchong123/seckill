package com.wangchong.seckill.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangchong
 * @Description 订单表
 * @Date : Created in 15:52 2018/8/31
 */
@Data
public class Order {

    private Long id;

    private Long goodsId;

    private Long userId;

    /**
     * 商品数量
     */
    private Integer nums;

    /**
     * 收货地址id
     */
    private Long addressId;

    private Date createTime;

    /**
     * 订单状态
     */
    private Integer status;

    private Date payTime;



}
