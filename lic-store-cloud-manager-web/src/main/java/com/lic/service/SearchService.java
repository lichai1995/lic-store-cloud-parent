package com.lic.service;

import com.lic.result.EgoPageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface SearchService {

    @RequestMapping("/dosearch")
    EgoPageInfo search(@RequestParam("currentPageJson") String currentPageJson,
                       @RequestParam("pageSizeJson") String pageSizeJson,
                       @RequestParam("keyWordsJson")String keyWordsJson);
}
