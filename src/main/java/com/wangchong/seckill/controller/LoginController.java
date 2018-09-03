package com.wangchong.seckill.controller;

import com.wangchong.seckill.entity.User;
import com.wangchong.seckill.service.UserService;
import com.wangchong.seckill.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 9:48 2018/9/3
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public Result login(HttpServletRequest request,String username,String password){
        User user = userService.login(username,password);
        if (user != null){
            request.getSession().setAttribute("user",user);
            return Result.success();
        }
        return Result.error();
    }
}
