package com.wangchong.seckill.util;

import lombok.Data;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 17:20 2018/8/31
 */
public enum CodeMsg {

    SUCCESS(1,"success"),
    ERROR(-1,"error");

    private int code;
    private String msg;

    private CodeMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }

    @Override
    public String toString() {
        return "{code:" +code+",msg:"+msg+"}";
    }

    public static void main(String[] args) {
        String s = SUCCESS.toString();
        System.out.println(s);
    }
}
