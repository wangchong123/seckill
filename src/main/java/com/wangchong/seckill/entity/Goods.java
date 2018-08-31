package com.wangchong.seckill.entity;

import lombok.Data;

/**
 * @Author: wangchong
 * @Description 商品表
 * @Date : Created in 10:08 2018/8/31
 */
@Data
public class Goods {

    private Long id;

    private String name;

    private String stock;

    private String image;

    private int price;
}
