package com.wangchong.seckill.dao;

import com.wangchong.seckill.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 10:11 2018/8/31
 */
@Mapper
@Repository
public interface GoodsDao {

    @Select("select * from goods")
    List<Goods> list();
}
