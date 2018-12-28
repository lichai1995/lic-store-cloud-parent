package com.lic.service;

import com.lic.result.EgoPageInfo;
import com.lic.result.EgoResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface CartService {
    @RequestMapping("/cart/addCart")
    EgoResult addCart(@RequestParam("goodsIdJson") String goodsIdJson,
                      @RequestParam("goodsNumJson") String goodsNumJson,
                      @RequestParam("uuIdJson") String uuIdJson);

    @RequestMapping("/cart/getTotal")
    Long getCartTotal(@RequestParam("userUUIdJson") String userUUIdJson);

    @RequestMapping("/cart/findByPage")
    EgoPageInfo findByPage(@RequestParam("currentPageJson") String currentPageJson,
                           @RequestParam("pageSizeJson") String pageSizeJson,
                           @RequestParam("uuIdJson") String uuIdJson);

    @RequestMapping("/cart/delete")
    EgoResult deleteGoods(@RequestParam("idJson") String idJson,
                          @RequestParam("uuIdJson") String uuIdJson);

    @RequestMapping("/cart/deleteAll")
    EgoResult deleteAll(@RequestParam("uuIdJson") String uuIdJson);
}
