package com.lic.controller;

import com.google.gson.Gson;
import com.lic.config.VsftpdConfig;
import com.lic.util.FdfsUploadUtil;
import com.lic.util.FtpUploadUtil;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pic")
@EnableConfigurationProperties({VsftpdConfig.class})
public class PicController {

    //ftp地址
    @Value("${ftp.host}")
    String host;

    //ftp端口号
    @Value("${ftp.port}")
    Integer port;

    //用户名
    @Value("${ftp.username}")
    String username;

    //密码
    @Value("${ftp.password}")
    String password;

    //上传路径
    @Value("${ftp.path}")
    String path;

    @RequestMapping("/upload")
    @ResponseBody
    public String picUpload(MultipartFile uploadFile){
        Map<String,Object> result = new HashMap<>();
        try {
            FtpUploadUtil.init(host,port,username,password);
            String url =FtpUploadUtil.upload(uploadFile.getInputStream(),uploadFile.getOriginalFilename(),path);
            result.put("error",0);
            result.put("url",url);
        } catch (Exception e) {
            result.put("error","1");
            result.put("message","亲，系统正在升级中，请稍后再试!");
            e.printStackTrace();
        }
        return new Gson().toJson(result);
    }
}
