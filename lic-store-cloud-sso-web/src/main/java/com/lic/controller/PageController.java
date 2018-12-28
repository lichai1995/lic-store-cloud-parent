package com.lic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     * 公共页面跳转
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String pageStr){
        return pageStr;
    }
}
