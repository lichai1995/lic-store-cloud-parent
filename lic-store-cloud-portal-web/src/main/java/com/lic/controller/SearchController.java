package com.lic.controller;

import com.google.gson.Gson;
import com.lic.result.EgoPageInfo;
import com.lic.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    private static Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @RequestMapping("/search")
    public String search(String keywords, Model model){
        model.addAttribute("keywords",keywords);
        return "search";
    }

    /**
     * 执行搜索
     */
    @RequestMapping("/dosearch")
    @ResponseBody
    public EgoPageInfo dosearch(Integer currentPage,Integer pageSize,String keywords){
        EgoPageInfo pageInfo = null;
        try{
            return searchService.search(new Gson().toJson(currentPage),new Gson().toJson(pageSize),new Gson().toJson(keywords));
//            System.out.println(new Gson().toJson(pageInfo1));

        }catch(Exception e){
            logger.error("执行搜索失败"+e.getMessage());
        }
        return pageInfo;
    }
}
