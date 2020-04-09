package com.refined.springboot_http.controller;

import com.refined.springboot_http.config.http.HttpClient;
import com.refined.springboot_http.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot-http
 * @description:
 * @author: urbane
 * @create: 2020-04-09 09:07
 **/
@RestController
public class TestController {
    @Autowired
    private HttpClient httpClient;

    @GetMapping("testGet")
    public String testGet() throws Exception {
        return httpClient.doGet("http://www.baidu.com",null);
    }

    @PostMapping("tesPost")
    public User tesPost() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", "1");
        paramMap.put("username", "mengda");
        User user = httpClient.doPost("http://localhost:8080/doPost", paramMap, User.class,null);
        return user;
    }

    @PostMapping("doPost")
    public User doPost(@RequestBody User user) {
        User newUser = new User(user.getId(), user.getUsername() + "y");
        return newUser;
    }
}
