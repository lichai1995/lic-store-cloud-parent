package com.lic.service;

import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface ContentService {

    @RequestMapping("/content/getData")
    EasyuiPageResult findByPage(@RequestParam("pageNum") String pageNum,
                                @RequestParam("pageSize") String pageSize,@RequestParam("categoryIdJson") String categoryIdJson);

    @RequestMapping("/content/saveOrUpdate")
    EgoResult saveOrUpdate(@RequestParam("contentJson") String contentJson);

    @RequestMapping("/content/delete")
    EgoResult deleteByIds(@RequestParam("idsJson") String idsJson);

    @RequestMapping("/content/findIndexSlideImg")
    List<String> findIndexSlideImg(@RequestParam("idsJson") String idsJson);
}
