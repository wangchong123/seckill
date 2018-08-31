package com.wangchong.seckill.dao;

import com.wangchong.seckill.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:27 2018/8/31
 */
@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where username=?")
    User getByUsername(@Param("username") String username);
}
