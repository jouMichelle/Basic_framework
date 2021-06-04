package com.example.infrastructure.exception;

import java.net.SocketException;

/**
 * @program: aiui
 * @description: 自定义异常类
 * @author: urbane
 * @create: 2020-08-27 20:32
 **/
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    private int code;
    /**
     * 异常信息返回的语音提示
     */
    private String url;

    public BaseException(int code) {
        this.code = code;
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BaseException(int code, String message, String url) {
        super(message);
        this.code = code;
        this.url = url;
    }

    public BaseException(String s) {
        this.url =s;
    }



    public String getUrl() {
        return url;
    }
    public int getCode() {
        return code;
    }
}
