package com.basic.springboot_netty_websocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot-netty-websocket
 * @description: 测试
 * @author:
 * @create: 2021-06-14 01:34
 **/
@RestController
public class TestController {
    @GetMapping(value = "/test")
    public String test(){
        return "hello netty websocket";
    }
}
