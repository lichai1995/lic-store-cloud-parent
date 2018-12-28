package com.lic.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chai
 * @Date 18/04/23 15:07
 */
public class FdfsUploadUtil {
    /**
     * 上传文件
     */
    public static String upload(String trackers,String hostProxy,byte[] fileData,String filename) throws IOException, MyException {
        ClientGlobal.initByTrackers(trackers);
        //创建客户端存储对象
        StorageClient client=new StorageClient();
        String suffix=filename.substring(filename.lastIndexOf(".")+1);
        //上传
        String[] res=client.upload_file(fileData,suffix,null);
        return "http://"+hostProxy+"/"+res[0]+"/"+res[1];
    }

}
