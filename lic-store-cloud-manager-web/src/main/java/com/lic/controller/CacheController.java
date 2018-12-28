package com.lic.controller;

import com.lic.result.EgoResult;
import com.lic.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    CacheService cacheService;

    /**
     * 清空商品分类缓存
     */
    @RequestMapping("/cleanGoodsCategory")
    @ResponseBody
    public EgoResult cleanGoodsCategory(){
        return cacheService.cleanGoodsCategory();
    }

}
