package com.wangchong.seckill.interceptor;

import com.wangchong.seckill.anno.LoginCheck;
import com.wangchong.seckill.entity.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 11:26 2018/9/3
 */
public class LoginCheckInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        LoginCheck loginCheck = method.getAnnotation(LoginCheck.class);
        if(loginCheck != null){
            User user = (User) request.getSession().getAttribute("user");
            if(user != null){
                return true;
            }
        }else{
            return true;
        }
        response.sendRedirect("/toLogin");
        return false;


    }
}
