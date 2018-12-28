package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Goods;
import com.lic.pojo.GoodsAttribute;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.GoodsAttributeService;
import com.lic.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods/attribute")
public class GoodsAttributeController {

    @Autowired
    GoodsAttributeService goodsAttributeService;

    /**
     * 公共页面跳转
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String pageStr) {
        return "goods/goods-attribute-" + pageStr;
    }

    /**
     * 分页查询
     */
    @RequestMapping("/getData")
    @ResponseBody
    public EasyuiPageResult getData(String pageNum, String pageSize){
        return goodsAttributeService.findByPage(new Gson().fromJson(pageNum,Integer.class),new Gson().fromJson(pageSize,Integer.class));
    }

    @RequestMapping("/save")
    @ResponseBody
    public EgoResult save(String goodsJson){
        GoodsAttribute goodsAttribute = new Gson().fromJson(goodsJson,GoodsAttribute.class);
        return goodsAttributeService.save(goodsAttribute);

    }

    @RequestMapping("/edit")
    @ResponseBody
    public GoodsAttribute edit(String idJson){
        Integer id = new Gson().fromJson(idJson,Integer.class);
        return goodsAttributeService.findById(id);

    }

    @RequestMapping("/update")
    @ResponseBody
    public EgoResult update(String goodsJson){
        GoodsAttribute goodsAttribute = new Gson().fromJson(goodsJson,GoodsAttribute.class);
        return goodsAttributeService.updateById(goodsAttribute);

    }

    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(String idJson){
        String ids = new Gson().fromJson(idJson,String.class);
        return goodsAttributeService.deleteByIds(ids);

    }



}
