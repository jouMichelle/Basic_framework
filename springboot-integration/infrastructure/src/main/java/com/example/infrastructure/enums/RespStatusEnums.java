package com.example.infrastructure.enums;

import lombok.Getter;

/**
 * @program: aiui
 * @description: 响应状态码封装
 * @author: urbane
 * @create: 2020-08-27 20:48
 **/
@Getter
public enum RespStatusEnums {

    /**
     * 操作成功
     */
    OK(200, "SUCCESS"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "服务器出错啦"),
    /**
     * HTTP请求方式错误
     */
    HTTP_REQUEST_MODE_ERROR(1000, "HTTP请求方式错误"),
    /**
     * 设备异常非平台设备
     */
    DEVICE_ERROR(2000, "设备异常,非平台设备"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(10000, "参数错误"),
    /**
     * 缺少请求参数
     */
    MISSING_REQUEST_PARAMETER_ERROR(10001, "缺少请求参数"),

    /**
     * 请求参数类型错误
     */
    REQUEST_PARAMETER_TYPE_ERROR(10002, "请求参数类型错误"),

    /**
     * 数据格式错误
     */
    DATA_FORMATTING_ERROR(10003, "数据格式错误"),
    /**
     * 操作数据库错误
     */
    OPERATION_DATABASE_ERROR(10004, "操作数据库错误"),

    /**
     * 非法输入或断言错误
     */
    Illegal_input_or_assertion_error(10005,"非法输入或断言错误"),

    /**
     * 语音识别失败
     */
    ASR_FAILURE_ERROR(80000, "asr failure"),

    /**
     * 下单失败
     */
    PLACE_ORDER_ERROR(70000, "下单失败");


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    RespStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
