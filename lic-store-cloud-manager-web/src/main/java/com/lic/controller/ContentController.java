package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Content;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${index.slide.image.id}")
    Long slideImageId;

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
            @RequestParam(defaultValue = "1" )Integer page,
            @RequestParam(defaultValue = "15") Integer rows,
            Long categoryId){
        return contentService.findByPage(new Gson().toJson(page),new Gson().toJson(rows),new Gson().toJson(categoryId));
    }

    /**
     * 保存或更新
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public EgoResult saveOrUpdate(Content content){
        return contentService.saveOrUpdate(new Gson().toJson(content));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(String ids){
        return contentService.deleteByIds(new Gson().toJson(ids));
    }

    /**
     * 获取大图标集合
     */
    @RequestMapping("/findIndexSlideImg")
    @ResponseBody
    public MappingJacksonValue findIndexSlideImg(String callBack){
        List<String> imageList = contentService.findIndexSlideImg(new Gson().toJson(slideImageId));
        MappingJacksonValue jacksonValue = new MappingJacksonValue(imageList);
        jacksonValue.setJsonpFunction(callBack);
        return jacksonValue;
    }
}
