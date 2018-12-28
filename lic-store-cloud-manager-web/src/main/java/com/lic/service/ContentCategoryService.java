package com.lic.service;

import com.lic.result.EgoResult;
import com.lic.result.TreeResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface ContentCategoryService {

    @RequestMapping("/content/category/getTree")
    List<TreeResult> getTree(@RequestParam("idJson") String idJson);

    @RequestMapping("/content/category/saveOrUpdate")
    EgoResult saveOrUpdate(@RequestParam("contentJson") String contentJson);

    @RequestMapping("/content/category/delete")
    EgoResult deleteById(@RequestParam("idJson") String idJson);
}
