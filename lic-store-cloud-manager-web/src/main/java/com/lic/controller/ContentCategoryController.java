package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.ContentCategory;
import com.lic.result.EgoResult;
import com.lic.result.TreeResult;
import com.lic.service.ContentCategoryService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    /**
     * 跳转至tree
     */
    @RequestMapping("tree")
    public String tree(){
        return "content/content-category-tree";
    }

    /**
     * 获取树形结构
     */
    @RequestMapping("/getTree")
    @ResponseBody
    public List<TreeResult> getTree(Long id){
        return contentCategoryService.getTree(new Gson().toJson(id));
    }

    /**
     * 保存或更新
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public EgoResult saveOrUpdate(ContentCategory contentCategory){
        return contentCategoryService.saveOrUpdate(new Gson().toJson(contentCategory));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(Long id){
        return contentCategoryService.deleteById(new Gson().toJson(id));
    }


}
