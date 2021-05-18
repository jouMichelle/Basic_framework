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
     * 设备异常非平台设备
     */
    DEVICE_ERROR(2000,"设备异常,非平台设备"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(10000,"参数错误"),

    /**
     *  语音识别失败
     */
    ASR_FAILURE_ERROR(80000,"asr failure"),

    /**
     * 下单失败
     */
    PLACE_ORDER_ERROR(70000,"下单失败")


            ;


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
