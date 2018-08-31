package com.wangchong.seckill.controller;

import com.wangchong.seckill.entity.Goods;
import com.wangchong.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 9:55 2018/8/31
 */
@Controller
public class IndexController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/")
    public String index(Model model){
        List<Goods> list = goodsService.list();
        model.addAttribute("list",list);
        return "index";
    }
}
