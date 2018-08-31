package com.wangchong.seckill.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:18 2018/8/31
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String salt;

    private Date registerTime;

    private Date lastLoginTime;

    private Integer loginCount;
}
