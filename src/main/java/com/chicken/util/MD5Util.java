package com.chicken.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhanglei
 * @Date: 2018/3/16 17:42
 * @Description: md5签名工具
 */
public class MD5Util {

    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is unsupported", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        String sign = hex.toString();
        //logger.info("风控签名{}",sign);
        return sign;
    }

    /**
     * 请求参数签名验证
     *
     * @param signStr
     * @param sign
     * @return true 验证通过 false 验证失败
     * @throws Exception
     */
    public static Boolean verifySign(String signStr, String sign) {
        return md5(signStr).equals(sign) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Boolean verifySign(String typeName, String signStr, String sign) {
        String md5SignStr = md5(signStr);
        if (md5SignStr.equals(sign)) {
            logger.info("check sign success, typeName={}.", typeName);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
