package com.lic.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author chai
 * @Date 18/04/22 15:30
 */
public class FtpUploadUtil {

    private static ThreadLocal<FTPClient> threadLocal = new ThreadLocal<FTPClient>();
    private static ThreadLocal<String> threadLocalHost = new ThreadLocal<String>();

    public static void init(String host, int port, String username, String password) throws IOException {
        FTPClient client = threadLocal.get();
        if (client == null) {
            client = new FTPClient();
            //1、连接
            client.connect(host, port);
            //2、登录
            client.login(username, password);
            threadLocal.set(client);
            threadLocalHost.set(host);
        }
    }

    public static String upload(InputStream local, String fileName, String path) throws IOException {
        String datePath = DateUtil.date2Str(new Date(), "/yyyy/MM/dd/");
        //路径添加日期
        path += datePath;
        FTPClient client = threadLocal.get();
        String host = threadLocalHost.get();
        //指定文件上传路径(路径不存在返回false)
        boolean exists = client.changeWorkingDirectory(path);
        if (!exists) {
            String pathArray[] = path.split("/");
            String temp = "/";
            for (String p : pathArray) {
                temp += (p + "/");
                //如果文件路径不存在，则创建
                client.makeDirectory(temp);
            }
            //重新指定文件上传路径
            client.changeWorkingDirectory(path);
        }
        //指定文件类型
        client.setFileType(FTP.BINARY_FILE_TYPE);

        //获取后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        //执行上传
        client.storeFile(uuid + suffix, local);
        //退出
        client.logout();
        //断开连接
        client.disconnect();
        threadLocalHost.remove();
        threadLocal.remove();
        //返回地址
        return "http://" + host + "/ego" + datePath + uuid + suffix;
    }

    public static void main(String[] args) throws IOException {
        InputStream local = new FileInputStream("F:\\code.png");
        init("192.168.245.128", 21, "ftpuser", "123456");
        String res = upload( local, "code.png", "/home/ftpuser/ego");
        System.out.println(res);

    }
}
