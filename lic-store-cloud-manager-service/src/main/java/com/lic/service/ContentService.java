package com.lic.service;

import com.lic.pojo.Content;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;

import java.util.List;

public interface ContentService {

    /**
     * 查询列表
     */
    EasyuiPageResult findByPage(Integer pageNum,Integer pageSize,Long categoryId);

    /**
     * 保存或更新
     */
    EgoResult saveOrUpdate(Content content);

    /**
     * 删除
     */
    EgoResult deleteByIds(String ids);

    /**
     * 获取首页大图标
     */
    List<String> findIndexSlideImg(Long categoryId);

}
