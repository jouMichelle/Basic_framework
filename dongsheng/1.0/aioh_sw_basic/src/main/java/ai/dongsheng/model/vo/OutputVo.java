package ai.dongsheng.model.vo;


import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.utils.GsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OutputVo<T> {
    private String reqId;
    private String msg;
    private int code;
    private int time;
    private T data = null;

    public OutputVo() {
    }

    public OutputVo(T data) {
        this.data = data;
        this.code =  ErrorCode.OK;
        this.msg = "SUCCESS";
        this.setTime((int) (System.currentTimeMillis() / 1000));
    }

    public OutputVo(String reqId) {
        this(reqId, ErrorCode.OK, "SUCCESS");
    }

    public OutputVo(String reqId,T data) {
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
    public OutputVo(String reqId,int code, String msg, T data) {
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
