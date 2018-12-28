package com.lic.service;

import com.lic.result.EgoPageInfo;


public interface SearchService {

    /**
     * 商城搜索
     */
    EgoPageInfo search(Integer currentPage, Integer pageSize, String keyWords);
}
