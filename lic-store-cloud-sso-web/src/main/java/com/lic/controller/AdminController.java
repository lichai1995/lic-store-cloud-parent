package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Admin;
import com.lic.pojo.AdminWithBLOBs;
import com.lic.result.EgoResult;
import com.lic.service.AdminService;
import com.lic.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
    public EgoResult register(AdminWithBLOBs admin){
        return adminService.register(new Gson().toJson(admin));

    }

    /**
     * 登录
     */
    @RequestMapping("/sign")
    @ResponseBody
    public EgoResult sign(String userName, String password, HttpServletRequest request, HttpServletResponse response){
        EgoResult result = adminService.sign(new Gson().toJson(userName),new Gson().toJson(password));
        if(result.getStatus() == 200){
            CookieUtil.setCookie(request,response,"sso_ticket",result.getMessage());
        }
        return result;
    }

    /**
     * 校验
     */
    @RequestMapping("/validate")
    public Admin validate(String ticket){
        return adminService.validate(new Gson().toJson(ticket));
    }
    /**
     * 跳转至登录页
     */
    @RequestMapping("/toLogin")
    public String toLogin(String redirectUrl, Map<String,Object> map){
        map.put("redirectUrl",redirectUrl);
        return "login";
    }
}
