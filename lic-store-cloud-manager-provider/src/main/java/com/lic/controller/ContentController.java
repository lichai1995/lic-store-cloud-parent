package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Content;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;
    /**
     * 公共页面跳转
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String pageStr){
        return "content/content-"+pageStr;
    }

    /**
     * 分页查询
     */
    @RequestMapping("/getData")
    @ResponseBody
    public EasyuiPageResult getData(
            String pageNum,
            String pageSize,
            String categoryIdJson){
        Integer page = new Gson().fromJson(pageNum,Integer.class);
        Integer size = new Gson().fromJson(pageSize,Integer.class);
        Long categoryId = new Gson().fromJson(categoryIdJson,Long.class);
        return contentService.findByPage(page,size,categoryId);
    }

    /**
     * 保存或更新
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public EgoResult saveOrUpdate(String contentJson){
        Content content = new Gson().fromJson(contentJson,Content.class);
        return contentService.saveOrUpdate(content);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(String idsJson){
        String ids = new Gson().fromJson(idsJson,String.class);
        return contentService.deleteByIds(ids);
    }

    /**
     * 获取大图标集合
     */
    @RequestMapping("/findIndexSlideImg")
    @ResponseBody
    public List<String> findIndexSlideImg(String idsJson){
        Long categoryId = new Gson().fromJson(idsJson,Long.class);
        List<String> imageList = contentService.findIndexSlideImg(categoryId);
        return imageList;
    }
}
