package com.wangchong.seckill.service;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.wangchong.seckill.dao.UserDao;
import com.wangchong.seckill.entity.User;
import com.wangchong.seckill.exception.GlobalException;
import com.wangchong.seckill.util.CodeMsg;
import com.wangchong.seckill.util.MD5Util;
import com.wangchong.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:34 2018/8/31
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(LoginVo loginVo){
        User user = userDao.getByUsername(loginVo.getUsername());
        if(user == null)
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        String dbPassword = MD5Util.formPassToDBPass(loginVo.getPassword(),user.getSalt());
        if(!dbPassword.equals(user.getPassword()))
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);

        return user;

    }
}
