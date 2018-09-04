package com.wangchong.seckill.dao;

import com.wangchong.seckill.entity.Goods;
import com.wangchong.seckill.entity.SeckillGoods;
import com.wangchong.seckill.vo.SeckillInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 10:11 2018/8/31
 */
@Mapper
@Repository
public interface SeckillGoodsDao {

    @Select("select * from seckill_goods")
    List<SeckillGoods> list();

    @Update("update seckill_goods set stock = stock - 1 where goods_id = #{goodsId} and stock > 0")
    int reduceStock(@Param("goodsId") Long goodsId);

}
