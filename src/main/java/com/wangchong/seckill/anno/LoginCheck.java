package com.wangchong.seckill.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 11:30 2018/9/3
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LoginCheck {

     String value() default "";
}
