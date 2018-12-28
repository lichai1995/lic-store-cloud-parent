package com.lic.service;

import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chai
 * @Data 18/04/23 23:41
 */
@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface GoodsService {
    /**
     * 保存商品
     * @param goodsJson
     * @return
     */
    @RequestMapping("/goods/save")
     EgoResult save(@RequestParam("goodsJson") String goodsJson);

    /**
     * 分页查询
     */
    @RequestMapping("/goods/getData")
    EasyuiPageResult selectByPage(@RequestParam("pageNum") String pageNum,
                                  @RequestParam("pageSize") String pageSize);

    @RequestMapping("/goods/update")
    EgoResult updateById(@RequestParam("goodsJson") String goodsJson);

    @RequestMapping("/goods/delete")
    EgoResult deleteByIds(@RequestParam("idsJson") String idsJson);
}
