package com.lic.service;

import com.lic.pojo.Admin;
import com.lic.result.EgoResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("LIC-STORE-CLOUD-MANAGER-PROVIDER")
public interface AdminService {

    @RequestMapping("/admin/register")
    EgoResult register(@RequestParam("adminJson") String adminJson);

    @RequestMapping("/admin/sign")
    EgoResult sign(@RequestParam("userNameJson") String userNameJson, @RequestParam("passwordJson") String passwordJson);

    @RequestMapping("/admin/validate")
    Admin validate(@RequestParam("ticketJson") String ticketJson);

}
