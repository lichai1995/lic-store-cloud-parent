package com.lic.service;

import com.lic.pojo.GoodsCategory;
import com.lic.result.GoodsCategoryVo;
import com.lic.result.TreeResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface GoodsCategoryService {

    /**
     * 商品类目选择
     */
    @RequestMapping("/goods/cat/list")
    List<TreeResult> findByPId(@RequestParam("id") Short pId);

    /**
     * 查询商品类目
     * @param idJson
     * @return
     */
    @RequestMapping("/goods/cat/edit")
    GoodsCategory findById(@RequestParam("idJson") String idJson);

    /**
     * 获取分类
     * @return
     */
    @RequestMapping("/goods/cat/getMenu")
    List<GoodsCategoryVo> findMenu(@RequestParam("parentIdJson") String parentIdJson, @RequestParam("levelJson") String levelJson);

}
