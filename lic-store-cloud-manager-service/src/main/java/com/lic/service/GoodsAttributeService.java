package com.lic.service;

import com.lic.pojo.GoodsAttribute;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;

/**
 * @author chai
 * @Date 18/04/26 22:46
 */
public interface GoodsAttributeService {
    /**
     * 分页查询
     */
    EasyuiPageResult findByPage(Integer pageNum,Integer pageSize);

    /**
     * 保存
     */
    EgoResult save(GoodsAttribute goodsAttribute);

    /**
     * 编辑
     */
    EgoResult updateById(GoodsAttribute goodsAttribute);

    /**
     * 根据id查询
     */
    GoodsAttribute findById(Integer id);

    /**
     * 删除
     */
    EgoResult deleteByIds(String ids);
}
