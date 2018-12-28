package com.lic.interceptor;

import com.google.gson.Gson;
import com.lic.pojo.Admin;
import com.lic.service.AdminService;
import com.lic.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //1、获取ticket
        String ticket = CookieUtil.getCookieValue(request, "sso_ticket");
        Jedis jedis = new Jedis("192.168.245.128",6379);
        Admin admin = null;
        if (ticket != null && ticket.trim().length() > 0) {
            byte[] adminBytes = jedis.get(SerializationUtils.serialize(ticket));
            if(adminBytes != null && adminBytes.length>0){
                admin = (Admin) SerializationUtils.deserialize(adminBytes);
            }
            if(admin != null && admin.getUserName().trim().length()>0){
                return true;
            }
        }
        //跳转至单点登录
        response.sendRedirect("http://localhost:8082/admin/toLogin"+"?redirectUrl="+"http://localhost:8083");
        return false;
    }

}
