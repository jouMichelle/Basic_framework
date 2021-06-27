package com.example.infrastructure.tuling;

import com.example.infrastructure.InfrastructureApplicationTests;
import com.example.infrastructure.model.tuling.AiWifiParam;
import com.example.infrastructure.service.retrofit.HttpApi;
import com.example.infrastructure.utils.tuling.UidUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @program: aiui
 * @description: ai_wifi接入测试
 * @author: urbane
 * @create: 2020-08-31 10:18
 **/
@Slf4j
public class AiWifiApiTest extends InfrastructureApplicationTests {

    @Autowired
    private HttpApi baseApi;

    // public static String apiKey = "61268a1476de459c90f42f94028dcf0e";
    // public static String secret = "7h0c26t4zT10Bc63";
    public static String apiKey = "f7a4c4b4f9df4eda80db6e071392a9a6";
    public static String secret = "7q0T66058309w41R";
    public static String url = "http://smartdevice.ai.tuling123.com/speech/chat";
    // public static String url = "http://127.0.0.1:8080/v1/speech/chat";
    // public static String url = "http://api.wecarelove.cooldays.cn/v1/speech/chat";

    @Test
    public void wifiApiTest() throws Exception {
        Long start = System.currentTimeMillis();
        AiWifiParam aiWifiParam = new AiWifiParam();
        String deviceId = "uid1234567891234";
        String uid = UidUtil.getUid(apiKey, secret, deviceId);
        aiWifiParam.setAk(apiKey);
        aiWifiParam.setUid(uid);
        // aiWifiParam.setUid("imei123456789126");
        // aiWifiParam.setToken("507e617ec53b490e9f5e38c94c2ab17c");

        // System.out.println(aiWifiParam.toString());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("parameters", aiWifiParam);
        // String fileName = "C:\\Users\\MichellJou\\Desktop\\测试音频\\粤语（吃饭了没）.amr";
        // String fileName = "C:\\Users\\MichellJou\\Desktop\\测试音频\\四川话（吃饭了没有）.amr";
        String fileName = "C:\\Users\\MichellJou\\Desktop\\测试音频\\测试.amr";
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("speech", "file");
        //读取本地图片输入流
        FileInputStream inStream = new FileInputStream(fileName);
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[inStream.available()];
        inStream.read(buff);
        inStream.close();
        log.info("文件大小:{}", buff.length);
        //构建body
        // RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
        //         .addFormDataPart("parameters", aiWifiParam.toString())
        //         .addFormDataPart("speech", "speech", RequestBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM.toString()), buff))
        //         .build();
        RequestBody requestBody = RequestBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM.toString()), buff);
        // 3.文件上传 多媒体对象
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("speech","speech",requestBody);
        // BaseApi baseApi = (BaseApi) ApiFactory.getApiInstance(BaseApi.class);
        //如果和rxjava1.x , call就换成 Observable
        Map<String, Object> params = new HashMap<>();
        paramMap.put("parameters",aiWifiParam.toString());
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("sn", "sn");
        headerMap.put("imei", "imei123456789456");
        // Call<ResponseBody> call = baseApi.upLoad1(url, headerMap, requestBody);
        // Call<ResponseBody> call = baseApi.upLoad3(url,headerMap, paramMap, requestBody);
        Call<ResponseBody> call = baseApi.upLoad4(url,headerMap, paramMap, multipartBody);
        Response<ResponseBody> execute = call.execute();
        // System.out.println(execute.body().string());
        Long end = System.currentTimeMillis();
        log.info("【请求耗时】：{}毫秒", end - start);
    }


    @Test
    public void Okhttp() throws IOException {
        Long start = System.currentTimeMillis();
        AiWifiParam aiWifiParam = new AiWifiParam();
        String deviceId = "uid1234567891234";
        String uid = UidUtil.getUid(apiKey, secret, deviceId);
        aiWifiParam.setAk(apiKey);
        aiWifiParam.setUid(uid);
        System.out.println(aiWifiParam.toString());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("parameters", aiWifiParam);
        // String fileName = "C:\\Users\\MichellJou\\Desktop\\测试音频\\粤语（吃饭了没）.amr";
        // String fileName = "C:\\Users\\MichellJou\\Desktop\\测试音频\\四川话（吃饭了没有）.amr";
        String fileName = "C:\\Users\\MichellJou\\Desktop\\测试音频\\测试.amr";
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("speech", "file");
        //读取本地图片输入流
        FileInputStream inStream = new FileInputStream(fileName);
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[inStream.available()];
        inStream.read(buff);
        inStream.close();
        log.info("文件大小:{}", buff.length);
        // OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间,10s
                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间,20s
                .connectTimeout(30, TimeUnit.SECONDS)//设置连接超时时间,30s
                .build();
        MediaType MEDIA_TYPE_PNG = MediaType.parse("octet-stream");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("parameters", aiWifiParam.toString())
                .addFormDataPart("speech", "speech",
                        RequestBody.create(MEDIA_TYPE_PNG, buff))
                .build();

        Request request = new Request.Builder()
                .header("Content-Type", "multipart/form-data ")
                .url(url)
                .post(requestBody)
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
        Long end = System.currentTimeMillis();
        log.info("【请求耗时】：{}毫秒", end - start);

    }

    @Test
    public void aiWifiTts() throws IOException {
        String ttsUrl = "http://smartdevice.ai.tuling123.com/speech/v2/tts";
        String deviceId = "uid1234567891234";
        String token = null;
        String text = "我还不会,我会努力学习的。";
        String uid = UidUtil.getUid(apiKey, secret, deviceId);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ak",apiKey);
        jsonObject.addProperty("uid",uid);
        // jsonObject.addProperty("token",token);
        jsonObject.addProperty("text",text);
        jsonObject.addProperty("tone",22);
        jsonObject.addProperty("tts",2);
        jsonObject.addProperty("tts_lan",0);
        jsonObject.addProperty("speed",5);
        jsonObject.addProperty("pitch",5);
        jsonObject.addProperty("volume",8);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("parameters", jsonObject.toString());
        System.out.println(paramMap.toString());
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("parameters", jsonObject.toString())
                .build();
        // BaseApi baseApi = (BaseApi) ApiFactory.getApiInstance(BaseApi.class);
        //如果和rxjava1.x , call就换成 Observable
        Call<ResponseBody> call = baseApi.upLoad(ttsUrl, requestBody);
        Response<ResponseBody> execute = call.execute();
        System.out.println(execute.body().string());
    }

}
