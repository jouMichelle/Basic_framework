package ai.dongsheng.model.vo;


import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.utils.GsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "返回值")
public class OutputVo<T> {

    @ApiModelProperty(value = "请求ID", dataType = "String", hidden = true,example = "null")
    private String reqId;
    @ApiModelProperty(value = "响应说明", dataType = "String",example = "SUCCESS")
    private String msg;
    @ApiModelProperty(value = "响应状态码", dataType = "int",example = "200",required = true)
    private int code;
    @ApiModelProperty(value = "响应时间戳", dataType = "int",example = "1568883765",required = true)
    private int time;
    @ApiModelProperty(value = "响应业务数据", dataType = "JosnObject",example = "{}")
    private T data = null;

    public OutputVo() {
    }

    public OutputVo(T data) {
        this.data = data;
        this.code =  ErrorCode.OK;
        this.msg = "SUCCESS";
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }

    // public OutputVo(String reqId) {
    //     this(reqId, ErrorCode.OK, "SUCCESS");
    // }

    public OutputVo(String reqId, T data) {
        this(reqId, ErrorCode.OK, "SUCCESS",data);
    }
    public OutputVo(String reqId, int code, String msg) {
        this.setReqId(reqId);
        this.setCode(code);
        this.setMsg(msg);
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }
    public OutputVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }
    public OutputVo(int code, String msg, T data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }
    public OutputVo(String reqId, int code, String msg, T data) {
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

    public OutputVo<T> reqId(String reqId) {
        this.reqId = reqId;
        return this;
    }

    public boolean hasData() {
        return code == 200 && data != null;
    }

    public static <T> OutputVo<T> code(int code) {
        return new OutputVo<>(code, null, null);
    }

    public static <T> OutputVo<T> code(int code, String msg) {
        return new OutputVo<>(code, msg, null);
    }

    public static <T> OutputVo<T> ok() {
        return new OutputVo<>(200, null, null);
    }

    public static <T> OutputVo<T> ok(T data) {
        return new OutputVo<>(200, null, data);
    }

    // @Override
    // public String toString() {
    //     return JSON.toJSONString(this);
    // }


    @Override
    public String toString() {
        return GsonUtil.toJson(this, this.getClass());
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
}
