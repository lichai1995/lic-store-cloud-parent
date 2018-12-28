package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.GoodsAttribute;
import com.lic.pojo.GoodsCategory;
import com.lic.result.GoodsCategoryVo;
import com.lic.result.TreeResult;
import com.lic.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/goods/cat")
public class GoodsCategoryController {

    @Autowired
    GoodsCategoryService goodsCategoryService;

    /**
     * 返回树形结构
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<TreeResult> getTree(Short id) {
        return goodsCategoryService.findByPId(id);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public GoodsCategory edit(String idJson) {
        Short id = new Gson().fromJson(idJson, Short.class);
        return goodsCategoryService.findById(id);
    }
    @RequestMapping("/getMenu")
    @ResponseBody
    public List<GoodsCategoryVo> getMenu(String parentIdJson,String levelJson) {
        Short parentId = new Gson().fromJson(parentIdJson, Short.class);
        Byte level = new Gson().fromJson(levelJson,Byte.class);
        return goodsCategoryService.findMenu(parentId,level);
    }

}
