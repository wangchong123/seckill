package com.wangchong.seckill.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 13:18 2018/9/4
 */
@Data
public class SeckillInfoVo implements Serializable {

    private Long goodsId;

    private String name;

    private Integer price;

    private Integer stock;

    private Date beginTime;

    private Date endTime;

    /**
     * -1 已结束
     * 0  未开始
     * 1  进行中
     */
    private int status;

    private int remainSecond;
}
