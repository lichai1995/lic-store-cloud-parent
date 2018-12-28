package com.lic.controller;

import com.google.gson.Gson;
import com.lic.result.EgoPageInfo;
import com.lic.result.EgoResult;
import com.lic.service.CartService;
import com.lic.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    /**
     * 添加至购物车
     */
    @RequestMapping("/addCart")
    @ResponseBody
    public EgoResult addCart(Integer goodsId, Integer goodsNum, HttpServletRequest request, HttpServletResponse response){
        String uuId= CookieUtil.getCookieValue(request,"cart_user_uuid");
        if(!(uuId!=null && uuId.trim().length()>0)){
            uuId=UUID.randomUUID().toString();
            CookieUtil.setCookie(request,response,"cart_user_uuid",uuId);
        }
        return cartService.addCart(new Gson().toJson(goodsId),new Gson().toJson(goodsNum),new Gson().toJson(uuId));
    }

    /**
     * 获取购物车总件数
     */
    @RequestMapping("/getTotal")
    @ResponseBody
    public Long getTotal(HttpServletRequest request,HttpServletResponse response){
        String uuId = CookieUtil.getCookieValue(request,"cart_user_uuid");
        //不存在，直接生成
        if(!(uuId!=null && uuId.trim().length()>0)){
            uuId = UUID.randomUUID().toString();
            CookieUtil.setCookie(request,response,"cart_user_uuid",uuId);
        }
        return cartService.getCartTotal(uuId);
    }

    /**
     * 跳转至购物车列表页
     */
    @RequestMapping("/list")
    public String list(){
        return "cart-list";
    }

    /**
     * 获取购物车列表
     */
    @RequestMapping("/findByPage")
    @ResponseBody
    public EgoPageInfo findByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  HttpServletRequest request,HttpServletResponse response){
        String uuId = CookieUtil.getCookieValue(request,"cart_user_uuid");
        //不存在，直接生成
        if(!(uuId!=null && uuId.trim().length()>0)){
            uuId = UUID.randomUUID().toString();
            CookieUtil.setCookie(request,response,"cart_user_uuid",uuId);
        }
        return cartService.findByPage(new Gson().toJson(currentPage),
                new Gson().toJson(pageSize),new Gson().toJson(uuId));
    }

    /**
     * 删除购物车商品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(Integer id,HttpServletRequest request,HttpServletResponse response){
        String uuId = CookieUtil.getCookieValue(request,"cart_user_uuid");
        //不存在，直接生成
        if(!(uuId!=null && uuId.trim().length()>0)){
            uuId = UUID.randomUUID().toString();
            CookieUtil.setCookie(request,response,"cart_user_uuid",uuId);
        }
        return cartService.deleteGoods(new Gson().toJson(id),new Gson().toJson(uuId));
    }

    /**
     * 清空购物车
     */
    @RequestMapping("/deleteAll")
    @ResponseBody
    public EgoResult deleteAll(HttpServletRequest request,HttpServletResponse response){
        String uuId = CookieUtil.getCookieValue(request,"cart_user_uuid");
        //不存在，直接生成
        if(!(uuId!=null && uuId.trim().length()>0)){
            uuId = UUID.randomUUID().toString();
            CookieUtil.setCookie(request,response,"cart_user_uuid",uuId);
        }
        return cartService.deleteAll(uuId);
    }
}
