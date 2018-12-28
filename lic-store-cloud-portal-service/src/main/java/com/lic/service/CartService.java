package com.lic.service;

import com.lic.result.EgoPageInfo;
import com.lic.result.EgoResult;

public interface CartService {

    /**
     * 添加至购物车
     */
    EgoResult addCart(Integer goodsId,Integer num,String userUUId);

    /**
     * 获取购物车总件数
     */
    Long getCartTotal(String userUUId);

    /**
     * 获取购物车列表
     */
    EgoPageInfo findByPage(Integer currentPage,Integer pageSize,String userUUId);

    /**
     * 删除购物车商品
     */
    EgoResult deleteGoods(Integer goodsId,String userUUId);

    /**
     * 清空购物车
     */
    EgoResult deleteAll(String userUUId);
}
