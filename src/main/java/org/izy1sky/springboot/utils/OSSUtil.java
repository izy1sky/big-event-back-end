package org.izy1sky.springboot.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.*;

public class OSSUtil {
    private static final String ACCESS_KEY = System.getenv("OSS_ACCESS_KEY");
    private static final String SECRET_KEY = System.getenv("OSS_SECRET_KEY");
    private static final String BUCKET = System.getenv("OSS_BUCKET");
    private static final String BASE_URL = System.getenv("OSS_BASE_URL");

    public static String uploadFile(String filename, InputStream inputStream) {
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
//默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = uploadManager.put(inputStream, filename, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return BASE_URL + putRet.key;
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }
        return BASE_URL;
    }
}
