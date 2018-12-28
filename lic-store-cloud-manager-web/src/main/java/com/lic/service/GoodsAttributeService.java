package com.lic.service;

import com.lic.pojo.GoodsAttribute;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface GoodsAttributeService {

    /**
     * 分页查询
     */
    @RequestMapping("/goods/attribute/getData")
    EasyuiPageResult findByPage(@RequestParam("pageNum") String pageNum,
                                  @RequestParam("pageSize") String pageSize);

    @RequestMapping("/goods/attribute/save")
    EgoResult save(@RequestParam("goodsJson") String goodsJson);

    @RequestMapping("/goods/attribute/edit")
    GoodsAttribute findById(@RequestParam("idJson") String idJson);

    @RequestMapping("/goods/attribute/update")
    EgoResult updateById(@RequestParam("goodsJson") String goodsJson);

    @RequestMapping("/goods/attribute/delete")
    EgoResult deleteByIds(@RequestParam("idJson") String idJson);
}
