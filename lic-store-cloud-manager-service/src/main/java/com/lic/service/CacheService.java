package com.lic.service;

import com.lic.result.EgoResult;

public interface CacheService {

    /**
     * 清除商品缓存
     * @return
     */
    EgoResult cleanGoodsCategory();
}
