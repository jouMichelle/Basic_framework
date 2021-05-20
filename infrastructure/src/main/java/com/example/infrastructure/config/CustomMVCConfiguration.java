package com.example.infrastructure.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @program: infrastructure
 * @description:
 * @author:E21
 * @create: 2021-05-19 22:23
 **/
@Configuration
public class CustomMVCConfiguration  extends WebMvcConfigurerAdapter {
    // @Bean
    // public HttpMessageConverter<String> responseBodyConverter() {
    //     StringHttpMessageConverter converter = new StringHttpMessageConverter(
    //             Charset.forName("UTF-8"));
    //     return converter;
    // }
    //
    // @Override
    // public void configureMessageConverters(
    //         List<HttpMessageConverter<?>> converters) {
    //     super.configureMessageConverters(converters);
    //     converters.add(responseBodyConverter());
    // }
    //
    // @Override
    // public void configureContentNegotiation(
    //         ContentNegotiationConfigurer configurer) {
    //     configurer.favorPathExtension(false);
    // }

    // 注册sa-token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
    }

    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }



    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
        //解决： 添加解决中文乱码后的配置之后，返回json数据直接报错 500：no convertter for return value of type
        //或这个：Could not find acceptable representation
        converters.add(messageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    //2.1：解决中文乱码后，返回json时可能会出现No converter found for return value of type: xxxx
    //或这个：Could not find acceptable representation
    //解决此问题如下
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    //2.2：解决No converter found for return value of type: xxxx
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }


}
