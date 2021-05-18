package com.example.infrastructure.model.vo;


import com.example.infrastructure.enums.RespStatusEnums;
import com.example.infrastructure.model.json.GsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;



//@ApiModel(value = "返回值")
public class OutputVO<T> {

//    @ApiModelProperty(value = "请求ID", dataType = "String", hidden = true)
    private String reqId;
//    @ApiModelProperty(value = "响应说明", dataType = "String",example = "SUCCESS")
    private String msg;
//    @ApiModelProperty(value = "响应状态码", dataType = "int",example = "200",required = true)
    private Integer code;
//    @ApiModelProperty(value = "响应时间戳", dataType = "int",example = "1568883765",required = true)
    private Integer time;
//    @ApiModelProperty(value = "响应业务数据", dataType = "JosnObject",example = "{}")
    private T data;



    public OutputVO() {
    }

    public OutputVO(T data) {
        this.data = data;
        this.code = RespStatusEnums.OK.getCode();
        this.msg = "SUCCESS";
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }

    public OutputVO(String reqId) {
        this(reqId, RespStatusEnums.OK.getCode(), RespStatusEnums.OK.getMessage());
    }

    public OutputVO(String reqId, T data) {
        this(reqId, RespStatusEnums.OK.getCode(), RespStatusEnums.OK.getMessage(),data);
    }
    public OutputVO(String reqId, int code, String msg) {
        this.setReqId(reqId);
        this.setCode(code);
        this.setMsg(msg);
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }
    public OutputVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }
    public OutputVO(int code, String msg, T data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }
    public OutputVO(String reqId, int code, String msg, T data) {
        this.setReqId(reqId);
        this.data = data;


        this.code = code;
        this.msg = msg;
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }


    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return 200 == code;
    }

    public OutputVO<T> reqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean hasData() {
        return code == 200 && data != null;
    }

    public static <T> OutputVO<T> code(int code) {
        return new OutputVO<>(code, null, null);
    }

    public static <T> OutputVO<T> code(int code, String msg) {
        return new OutputVO<>(code, msg, null);
    }

    public static <T> OutputVO<T> ok() {
        return new OutputVO<>(200, null, null);
    }

    public static <T> OutputVO<T> ok(T data) {
        return new OutputVO<>(200, null, data);
    }


    @Override
    public String toString() {
        return GsonUtil.toJson(this, this.getClass());
    }

}
