package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Goods;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 公共页面跳转
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String pageStr) {
        return "goods/goods-" + pageStr;
    }

    /**
     * 保存商品
     */
    @RequestMapping("/save")
    @ResponseBody
    public EgoResult save(String goodsJson){
        Goods goods = new Gson().fromJson(goodsJson,Goods.class);
        return goodsService.save(goods);
    }

    /**
     * 分页查询
     */
    @RequestMapping("/getData")
    @ResponseBody
    public EasyuiPageResult getData(String pageNum, String pageSize){
        return goodsService.selectByPage(new Gson().fromJson(pageNum,Integer.class),new Gson().fromJson(pageSize,Integer.class));
    }

    /**
     * 更新商品
     */
    @RequestMapping("/update")
    @ResponseBody
    public EgoResult update(String goodsJson){
        Goods goods = new Gson().fromJson(goodsJson,Goods.class);
        return goodsService.updateById(goods);
    }

    /**
     * 删除商品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(String idsJson){
        String ids = new Gson().fromJson(idsJson,String.class);
        return goodsService.deleteByIds(ids);
    }

}
