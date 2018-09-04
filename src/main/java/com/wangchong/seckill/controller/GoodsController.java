package com.wangchong.seckill.controller;

import com.wangchong.seckill.entity.Goods;
import com.wangchong.seckill.service.GoodsService;
import com.wangchong.seckill.util.Result;
import com.wangchong.seckill.vo.SeckillInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String list(Model model){
        List<Goods> list = goodsService.list();
        model.addAttribute("list",list);
        return "index";
    }

    @RequestMapping("/toDetail")
    public String toDetail(Long goodsId,Model model){

        model.addAttribute("goodsId",goodsId);
        return "detail";

    }

    @ResponseBody
    @RequestMapping("/detail")
    public Result detail(Long goodsId){
        SeckillInfoVo goods = goodsService.getByGoodsId(goodsId);
        long begin = goods.getBeginTime().getTime();
        long end = goods.getEndTime().getTime();
        long now = System.currentTimeMillis();
        int status = 0;
        int remainSecond = 0;
        if(begin > now){//未开始
            status = 0;
            remainSecond = (int)(begin - now)/1000;
        }else if(end < now){//已结束
            status = -1;
        }else{//进行中
            status = 1;
        }
        goods.setStatus(status);
        goods.setRemainSecond(remainSecond);
        return Result.success(goods);
    }


}
