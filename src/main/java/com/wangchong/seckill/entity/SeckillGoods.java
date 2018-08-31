package com.wangchong.seckill.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:03 2018/8/31
 */
@Data
public class SeckillGoods {

    private Long id;

    private Long goodsId;

    private Integer seckillPrice;

    private Integer nums;

    private Date beginTime;

    private Date endTime;
}
