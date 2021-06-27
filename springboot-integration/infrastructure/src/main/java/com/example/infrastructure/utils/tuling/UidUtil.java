package com.example.infrastructure.utils.tuling;

/**
 * @program: aiui
 * @description: 图灵uid生成工具类
 * @author: urbane
 * @create: 2020-08-31 11:07
 **/
public class UidUtil {

    public static String getUid(String apiKey,String secret,String deviceId){
        String apiKey16 = apiKey.substring(0, 16);
        //secret,aes加密
        String uid = AESUtils.aesEncrypt(deviceId, secret, apiKey16);
        return uid;
    }


    public static void main(String[] args) {
        String apiKey = "";     //apiKey
        String secret = "";                     //Secret
        String deviceId = "";                   //deviceId
        String apiKey16 = apiKey.substring(0, 16);
        String uid = AESUtils.aesEncrypt(deviceId, secret, apiKey16);//secret,aes加密
        System.out.println(uid.toLowerCase());
    }
}
