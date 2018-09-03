package com.wangchong.seckill.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 15:20 2018/9/3
 */
@Data
public class LoginVo {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty
    private String password;
}
