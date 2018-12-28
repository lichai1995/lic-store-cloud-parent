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
    public EgoResult addCart(String goodsIdJson, String goodsNumJson,String uuIdJson,String flagJson){
        Integer goodsId = new Gson().fromJson(goodsIdJson,Integer.class);
        Integer goodsNum = new Gson().fromJson(goodsNumJson,Integer.class);
        String uuId = new Gson().fromJson(uuIdJson,String.class);
        return cartService.addCart(goodsId,goodsNum,uuId);
    }

    /**
     * 获取购物车总件数
     */
    @RequestMapping("/getTotal")
    @ResponseBody
    public Long getTotal(String userUUIdJson){
       String userUUId = new Gson().fromJson(userUUIdJson,String.class);
       return cartService.getCartTotal(userUUId);
    }

    /**
     * 获取购物车列表
     */
    @RequestMapping("/findByPage")
    @ResponseBody
    public EgoPageInfo findByPage(String currentPageJson,String pageSizeJson, String uuIdJson){
        Integer currentPage = new Gson().fromJson(currentPageJson,Integer.class);
        Integer pageSize = new Gson().fromJson(pageSizeJson,Integer.class);
        String uuId = new Gson().fromJson(uuIdJson,String.class);
        System.out.println("provider"+currentPage+"......."+uuId);
        return cartService.findByPage(currentPage,pageSize,uuId);
    }

    /**
     * 删除购物车商品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(String idJson,String uuIdJson){
        Integer id = new Gson().fromJson(idJson,Integer.class);
        String uuId = new Gson().fromJson(uuIdJson,String.class);
        return cartService.deleteGoods(id,uuId);
    }

    /**
     * 清空购物车
     */
    @RequestMapping("/deleteAll")
    @ResponseBody
    public EgoResult deleteAll(String uuIdJson){
        String uuId = new Gson().fromJson(uuIdJson,String.class);
        return cartService.deleteAll(uuId);
    }
}
