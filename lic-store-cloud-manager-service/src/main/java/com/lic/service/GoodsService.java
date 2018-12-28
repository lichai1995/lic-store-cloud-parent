package com.lic.service;

import com.lic.pojo.Goods;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;

/**
 * @author chai
 * @Data 18/04/23 23:41
 */
public interface GoodsService {
    /**
     * 保存商品
     * @param goods
     * @return
     */
     EgoResult save(Goods goods);

    /**
     * 分页查询
     */
    EasyuiPageResult selectByPage(Integer pageNum,Integer pageSize);

    /**
     * 更新商品
     */
    EgoResult updateById(Goods goods);

    /**
     * 删除
     */
    EgoResult deleteByIds(String ids);

}
