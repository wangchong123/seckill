package com.wangchong.seckill.exception;

import com.wangchong.seckill.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 9:17 2018/9/3
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandle(HttpServletRequest request,Exception e){
        e.printStackTrace();
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCm());
        }else {
            return Result.error();
        }
    }
}
