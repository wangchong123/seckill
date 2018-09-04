package com.wangchong.seckill.service;

import com.wangchong.seckill.dao.OrderDao;
import com.wangchong.seckill.dao.SeckillGoodsDao;
import com.wangchong.seckill.entity.Order;
import com.wangchong.seckill.entity.SeckillGoods;
import com.wangchong.seckill.entity.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 17:06 2018/9/4
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;

    /**
     * 是否有秒杀成功的订单
     * @param goodsId
     * @param userId
     * @return
     */
    public int getSeckillOrder(long goodsId,long userId){
        return orderDao.getByUserIdAndGoodsId(userId,goodsId);
    }

    @Transactional
    public Order insertOrder(Long userId,Long goodsId){
        Order order = new Order();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setNums(1);
        orderDao.insertOrder(order);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(userId);
        seckillOrder.setGoodsId(goodsId);
        return order;
    }

    public boolean reduceScock(Long goodsId){
        if(goodsId == null)
            return false;
       return seckillGoodsDao.reduceStock(goodsId) > 0;

    }
}
