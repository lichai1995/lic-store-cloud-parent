package com.lic.service;

import com.lic.pojo.GoodsCategory;
import com.lic.result.GoodsCategoryVo;
import com.lic.result.TreeResult;

import java.util.List;

public interface GoodsCategoryService {

    /**
     * 商品类目选择
     */
    List<TreeResult> findByPId(Short pId);

    /**
     * 查询商品类目
     * @param id
     * @return
     */
    GoodsCategory findById(Short id);

    /**
     * 查询商品类目父子关系
     * @return
     */
    List<GoodsCategoryVo> findMenu(Short parentId,Byte level);
}
