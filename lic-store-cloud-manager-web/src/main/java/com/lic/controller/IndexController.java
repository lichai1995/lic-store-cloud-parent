package com.lic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 程序首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
