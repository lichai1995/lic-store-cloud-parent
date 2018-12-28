package com.lic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "ftp")
@PropertySource("classpath:vsftpd.properties")
public class VsftpdConfig {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String path;
}
