package com.chicken.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * @author zhanglei
 * @date 2019-09-05 20:09
 */
public class COSClientUtil {


    private static final String bucketName = "poult-1300165852";

    private static final String secretId = "AKIDHYkpHzRCcXZZqwyQm174qMejptlVFSaT";
    private static final String secretKey = "JWYO3ymNAkMkvtiPQL5Dar55uhHxRkpN";

    private static final COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
    private static final ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
    private static final COSClient cosClient = new COSClient(cred, clientConfig);
    private COSClient cOSClient;

    public COSClientUtil() {
        cOSClient = new COSClient(cred, clientConfig);
    }

    /**
     * 销毁
     */
    public void destory() {
        cOSClient.shutdown();
    }


    public String uploadFile2Cos(MultipartFile file) throws Exception {
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new Exception("上传图片大小不能超过10M！");
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {

            File f = MultipartFileToFile(file);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, name, f);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("图片上传失败");
        }
    }


    private File MultipartFileToFile(MultipartFile file){

        File toFile = null;
        try {
            if (file.equals("") || file.getSize() <= 0) {
                file = null;
            } else {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }
        }catch (Exception e){

        }

        return toFile;
    }


    public void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        return getUrl(fileUrl);
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = cosClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    /**
     * 删除文件
     * @param key
     */
    public void deleteImg(String key){
        System.out.println("--------"+key);
        cosClient.deleteObject(bucketName,key);
    }

}
