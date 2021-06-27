package com.example.infrastructure.service.retrofit;

import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: infrastructure
 * @description:
 * @author:
 * @create: 2021-06-27 17:03
 **/
@RetrofitClient(baseUrl = "http://localhost:8080")
public interface HttpApi {

    @OkHttpClientBuilder
    static OkHttpClient.Builder okhttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS);

    }

    @POST
    Call<ResponseBody> upLoad1(
            @Url() String url,
            @HeaderMap Map<String, Object> headerMap,
            @Body RequestBody Body);


    @POST
    @Multipart
    Call<ResponseBody> upLoad3( @Url() String url,@HeaderMap Map<String, Object> headerMap,@QueryMap Map<String, Object> maps,@Part("speech\"; filename=\"speech\"") RequestBody requestBody);

    @POST
    @Multipart
    Call<ResponseBody> upLoad4(@Url() String url, @HeaderMap Map<String, Object> headerMap, @QueryMap Map<String, Object> maps,
                               @Part MultipartBody.Part  multipartBody);

    @POST
    Call<ResponseBody>  upLoad1(@HeaderMap Map<String, Object> headerMap,
                                @Body RequestBody Body);

    @POST()
    Call<ResponseBody> upLoad(
            @Url() String url,
            @Body RequestBody Body);

}
