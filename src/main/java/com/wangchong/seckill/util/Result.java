package com.wangchong.seckill.util;

import lombok.Data;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:38 2018/8/31
 */
@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data){
        this.data = data;
    }

    public static  <T> Result<T> success(T data){
        return new Result<T>(data);
    }


}
