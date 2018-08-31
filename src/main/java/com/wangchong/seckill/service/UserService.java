package com.wangchong.seckill.service;

import com.wangchong.seckill.dao.UserDao;
import com.wangchong.seckill.entity.User;
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

    public boolean login(String username,String password){
        User user = userDao.getByUsername(username);
        if(user == null)
            return false;

        return true;

    }
}
