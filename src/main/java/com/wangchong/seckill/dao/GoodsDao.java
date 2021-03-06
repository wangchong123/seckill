package com.wangchong.seckill.dao;

import com.wangchong.seckill.entity.Goods;
import com.wangchong.seckill.vo.SeckillInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("select s.*,g.name from seckill_goods s ,goods g where s.goods_id=g.id and g.id=#{id}")
    SeckillInfoVo getByGoodsId(@Param("id") Long id);
}
