package com.lic.service;

import com.lic.pojo.Admin;
import com.lic.pojo.AdminWithBLOBs;
import com.lic.result.EgoResult;

public interface AdminService {

    /**
     * 注册接口
     * @param admin
     * @return
     */
    EgoResult register(AdminWithBLOBs admin);

    /**
     * 登录接口
     */
    EgoResult sign(String username,String password);

    /**
     * 验证接口
     */
    Admin validate(String ticket);

}
