package com.mtons.mblog;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.mtons.Khamonline.base.utils.ImageUtils;
import com.upyun.UpYunUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * created by langhsu
 * on 2019/1/22
 */
public class AliyunOSSTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName = "mtons";

        File file = new File("F:/data/a_2.jpg");
        byte[] bytes = ImageUtils.screenshot(file, 360, 200);
        String key = UpYunUtils.md5(bytes);

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        PutObjectResult result = ossClient.putObject(bucketName, "static/"+key + ".jpg", new ByteArrayInputStream(bytes));
        ossClient.shutdown();
    }
}
