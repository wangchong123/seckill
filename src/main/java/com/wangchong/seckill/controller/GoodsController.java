package com.wangchong.seckill.controller;

import com.wangchong.seckill.entity.Goods;
import com.wangchong.seckill.service.GoodsService;
import com.wangchong.seckill.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 16:20 2018/9/3
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/list")
    public Result list(){
        List<Goods> list = goodsService.list();
        return Result.success(list);
    }

    @RequestMapping("/detail")
    public Result detail(Long id){
        Goods goods = goodsService.getById(id);
        return Result.success(goods);
    }


}
