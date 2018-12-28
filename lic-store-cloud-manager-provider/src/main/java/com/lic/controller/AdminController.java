package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Admin;
import com.lic.pojo.AdminWithBLOBs;
import com.lic.result.EgoResult;
import com.lic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 跳转至程序首页
     */
    @RequestMapping("/register")
    @ResponseBody
    public EgoResult register(String adminJson){
        AdminWithBLOBs admin = new Gson().fromJson(adminJson,AdminWithBLOBs.class);
        return adminService.register(admin);
    }

    /**
     * 登录
     */
    @RequestMapping("/sign")
    @ResponseBody
    public EgoResult sign(String userNameJson,String passwordJson){
        String userName = new Gson().fromJson(userNameJson,String.class);
        String password = new Gson().fromJson(passwordJson,String.class);
        EgoResult result = adminService.sign(userName,password);
        return result;
    }

    /**
     * 校验
     */
    @RequestMapping("/validate")
    @ResponseBody
    public Admin validate(String ticketJson){
        String ticket = new Gson().fromJson(ticketJson,String.class);
        return adminService.validate(ticket);
    }

}