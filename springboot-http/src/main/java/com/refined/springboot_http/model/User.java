package com.refined.springboot_http.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @program: springboot-http
 * @description:
 * @author: urbane
 * @create: 2020-04-09 09:03
 **/
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private Long id;
    private String username;
}
