package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Goods;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public EgoResult save(Goods goods){
        return goodsService.save(new Gson().toJson(goods));
    }

    /**
     * 分页查询
     */
    @RequestMapping("/getData")
    @ResponseBody
    public EasyuiPageResult getData(Integer page,Integer rows){
        return goodsService.selectByPage(new Gson().toJson(page),new Gson().toJson(rows));
    }

    /**
     * 更新商品
     */
    @RequestMapping("/update")
    @ResponseBody
    public EgoResult update(Goods goods){

        return goodsService.updateById(new Gson().toJson(goods));
    }

    /**
     * 删除商品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(String ids){

        return goodsService.deleteByIds(new Gson().toJson(ids));
    }

}
