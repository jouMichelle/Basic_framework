package com.example.infrastructure.exception;


import cn.dev33.satoken.exception.NotLoginException;
import com.example.infrastructure.constant.AudioConst;
import com.example.infrastructure.enums.RespStatusEnums;
import com.example.infrastructure.model.vo.OutputVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @program: aioh_sw_im
 * @description: 全局异常类
 * @author:
 * @create: 2019-09-14 14:59
 **/
@SuppressWarnings("rawtypes")
@RestControllerAdvice
@Component
public class GlobleExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobleExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public OutputVO defultExcepitonHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        ex.printStackTrace();

        logger.error("GlobleExceptionHandler defultExcepitonHandler Exception:{}", ex.getMessage());
        //处理返回的错误信息
        StringBuffer errorMsg = new StringBuffer();
        // 参数校验失败
        if (ex instanceof BindException) {
            BindException c = (BindException) ex;
            List<ObjectError> errors = c.getBindingResult().getAllErrors();
            for (ObjectError error : errors) {
                errorMsg.append(error.getDefaultMessage()).append(";");
            }
            return new OutputVO<>(RespStatusEnums.PARAMETER_ERROR.getCode(),
                    RespStatusEnums.PARAMETER_ERROR.getMessage() + "," + errorMsg.toString(), AudioConst.error_1);
        }
        return new OutputVO<>(RespStatusEnums.UNKNOWN_ERROR.getCode(), RespStatusEnums.UNKNOWN_ERROR.getMessage(), AudioConst.error_1);

    }

    /**
     *  拦截 sa-token 校验异常
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NotLoginException.class)
    public OutputVO NotLoginException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        ex.printStackTrace();
        logger.error("Request Exception Path:{}",request.getRequestURL());
        logger.error("GlobleExceptionHandler NotLoginException Exception:{}", ex.getMessage());
        //处理返回的错误信息
        return new OutputVO<>(RespStatusEnums.UNKNOWN_ERROR.getCode(), ex.getMessage(), AudioConst.error_1);

    }

    /**
     * 处理实体字段校验不通过异常
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public OutputVO validationError(HttpServletRequest request, HttpServletResponse response,
                                    MethodArgumentNotValidException ex) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        BindingResult result = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : result.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        return new OutputVO<>(RespStatusEnums.PARAMETER_ERROR.getCode(),
                RespStatusEnums.PARAMETER_ERROR.getMessage() + sb.toString(), AudioConst.error_1);
    }


    /**
     * 处理自定义的业务异常
     *
     * @param
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public OutputVO bizExceptionHandler(HttpServletRequest request, HttpServletResponse response, BaseException e) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        logger.error("[{}]  Business exceptions ! Error cause =>> code:[{}],errMessage:[{}]", method, e.getCode(), e.getMessage());
        return new OutputVO<>(e.getCode(), e.getMessage(), e.getUrl());
    }


}
