package com.wangchong.seckill.exception;

import com.wangchong.seckill.util.CodeMsg;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 17:50 2018/8/31
 */
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm(){
        return cm;
    }

}
