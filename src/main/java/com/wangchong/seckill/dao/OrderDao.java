package com.wangchong.seckill.dao;

import com.wangchong.seckill.entity.Order;
import com.wangchong.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:41 2018/9/4
 */
@Mapper
@Repository
public interface OrderDao {

    @Insert("insert into order (user_id,goods_id,nums,address_id,create_time,`status`)" +
            " values(#{userId},#{goodsId},#{nums},1,now(),1)")
    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType = long.class,statement = "select last_insert_id")
    long insertOrder(Order order);

    @Insert("insert into seckill_order (order_id,user_id,goods_id) values(#{orderId},#{userId},#{goodsId})")
    int insertSeckillOrder(SeckillOrder seckillOrder);

    @Select("select count(*) from seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
    int getByUserIdAndGoodsId(@Param("userId") long userId,@Param("goodsId") long goodsId);

}
