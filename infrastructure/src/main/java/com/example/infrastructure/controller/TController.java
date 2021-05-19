package com.example.infrastructure.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author anyon
 * @Date 2021/5/19 14:38
 */
@Api(tags = "首页模块")
@RestController
public class TController {
    // @Autowired
    // private AiUsageRecordsMapper aiUsageRecordsMapper;
    // @GetMapping(value = "/test")
    // public OutputVO test(){
    //     AiUsageRecords aiUsageRecords = aiUsageRecordsMapper.selectByPrimaryKey(10L);
    //     return new OutputVO(aiUsageRecords);
    //
    // }

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }

} 
