package com.lic.service;

import com.lic.result.EgoResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface CacheService {

    @RequestMapping("/cache/cleanGoodsCategory")
    EgoResult cleanGoodsCategory();
}
