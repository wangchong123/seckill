package com.wangchong.seckill.util;

import lombok.Data;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:38 2018/8/31
 */
@Data
public class Result {

    private Integer code;

    private String msg;

    private Object data;

    public Result(CodeMsg o,Object data){
        this.code = o.getCode();
        this.msg = o.getMsg();
        this.data = data;
    }

    public Result(CodeMsg o){
        this.code = o.getCode();
        this.msg = o.getMsg();
    }

    public Result(Object data){
        this.data = data;
    }

    public static  Result success(Object data){
        return new Result(CodeMsg.SUCCESS,data);
    }

    public static  Result success(){
        return new Result(CodeMsg.SUCCESS);
    }

    public static  Result error(CodeMsg o){
        return new Result(o);
    }

    public static  Result error(){
        return new Result(CodeMsg.ERROR);
    }


    public static void main(String[] args) {
        Result.success();
    }



}
