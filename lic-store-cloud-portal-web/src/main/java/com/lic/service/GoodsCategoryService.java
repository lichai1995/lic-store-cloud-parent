package com.lic.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface GoodsCategoryService {


}
