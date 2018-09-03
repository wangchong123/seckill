package com.wangchong.seckill.controller;

import com.wangchong.seckill.entity.User;
import com.wangchong.seckill.service.UserService;
import com.wangchong.seckill.util.CodeMsg;
import com.wangchong.seckill.util.Result;
import com.wangchong.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    public Result login(HttpServletRequest request, @Valid LoginVo loginVo){
        User user = userService.login(loginVo);
        if (user != null){
            request.getSession().setAttribute("user",user);
            return Result.success();
        }
        return Result.error();
    }
}
