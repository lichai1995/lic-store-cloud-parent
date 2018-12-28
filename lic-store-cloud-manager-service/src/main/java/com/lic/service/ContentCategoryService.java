package com.lic.service;

import com.lic.pojo.ContentCategory;
import com.lic.result.EgoResult;
import com.lic.result.TreeResult;

import java.util.List;

public interface ContentCategoryService {

    /**
     * 获取属性结构
     */
    List<TreeResult> getTree(Long pId);

    /**
     * 修改或添加
     */
    EgoResult saveOrUpdate(ContentCategory contentCategory);

    /**
     * 删除
     */
    EgoResult deleteById(Long id);
}
