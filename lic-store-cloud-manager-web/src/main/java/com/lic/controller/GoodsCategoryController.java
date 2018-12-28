package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.GoodsAttribute;
import com.lic.pojo.GoodsCategory;
import com.lic.result.GoodsCategoryVo;
import com.lic.result.TreeResult;
import com.lic.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJacksonValue;
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

    /**
     * 获取分类
     */
    @RequestMapping("/getMenu")
    @ResponseBody
    public MappingJacksonValue getMenu(String callBack) {
        Short parentId = 0;
        Byte level = 1;
        List<GoodsCategoryVo> voList = goodsCategoryService.findMenu(new Gson().toJson(parentId), new Gson().toJson(level));
        MappingJacksonValue jackson = new MappingJacksonValue(voList);
        jackson.setJsonpFunction(callBack);
        return jackson;
    }
}
