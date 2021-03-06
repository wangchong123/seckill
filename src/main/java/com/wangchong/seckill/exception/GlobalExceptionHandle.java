package com.wangchong.seckill.exception;

import com.wangchong.seckill.util.CodeMsg;
import com.wangchong.seckill.util.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 9:17 2018/9/3
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandle(HttpServletRequest request,Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCm());
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            CodeMsg.BIND_ARGS_ERROR.setMsg(msg);
            return Result.error(CodeMsg.BIND_ARGS_ERROR);
        }else {
            return Result.error();
        }
    }
}
