package com.example.infrastructure.exception;


import cn.dev33.satoken.exception.NotLoginException;
import com.example.infrastructure.constant.AudioConst;
import com.example.infrastructure.enums.RespStatusEnums;
import com.example.infrastructure.model.vo.OutputVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.zip.DataFormatException;


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
        logger.error("GlobleExceptionHandler defultExcepitonHandler Exception:{}", ex);
        return new OutputVO<>(RespStatusEnums.UNKNOWN_ERROR.getCode(), RespStatusEnums.UNKNOWN_ERROR.getMessage(), AudioConst.error_1);

    }

    /**
     * 校验错误拦截处理
     *
     * @param ex 异常
     * @return 返回值
     * StringBuilder sb = new StringBuilder();
     * for (FieldError error : ex.getBindingResult().getFieldErrors()) {
     * sb.append(error.getDefaultMessage()).append(";");
     * }
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public OutputVO methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalid> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> invalidArguments.add(new ArgumentInvalid(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage())));
        return new OutputVO<>(RespStatusEnums.PARAMETER_ERROR.getCode(),
                RespStatusEnums.PARAMETER_ERROR.getMessage(), invalidArguments);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public OutputVO bindExceptionHandler(BindException ex) {
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalid> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> invalidArguments.add(new ArgumentInvalid(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage())));
        return new OutputVO<>(RespStatusEnums.PARAMETER_ERROR.getCode(),
                RespStatusEnums.PARAMETER_ERROR.getMessage(), invalidArguments);
    }


    /**
     * HTTP请求方式不正确
     *
     * @param ex 异常
     * @return 返回值
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public OutputVO httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error("HTTP请求方式不正确：【{}】", ex.getMessage());
        return new OutputVO<>(RespStatusEnums.HTTP_REQUEST_MODE_ERROR.getCode(),
                RespStatusEnums.HTTP_REQUEST_MODE_ERROR.getMessage() + "," + ex.getMessage());
    }

    /**
     * 请求参数不全
     *
     * @param ex 异常
     * @return 返回值
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public OutputVO missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        logger.error("请求参数不全：【{}】", ex.getMessage());
        return new OutputVO<>(RespStatusEnums.MISSING_REQUEST_PARAMETER_ERROR.getCode(),
                RespStatusEnums.MISSING_REQUEST_PARAMETER_ERROR.getMessage() + ", " + ex.getMessage());
    }

    /**
     * 请求参数类型不正确
     *
     * @param ex 异常
     * @return 返回值
     */
    @ExceptionHandler(value = TypeMismatchException.class)
    public OutputVO typeMismatchException(TypeMismatchException ex) {
        logger.error("请求参数类型不正确：【{}】", ex.getMessage());
        return new OutputVO(RespStatusEnums.REQUEST_PARAMETER_TYPE_ERROR.getCode(),
                RespStatusEnums.REQUEST_PARAMETER_TYPE_ERROR.getMessage() + ", " + ex.getMessage());
    }

    /**
     * 数据格式不正确
     *
     * @param ex 异常
     * @return 返回值
     */
    @ExceptionHandler(value = DataFormatException.class)
    public OutputVO dataFormatException(DataFormatException ex) {
        logger.error("数据格式不正确：【{}】", ex.getMessage());
        return new OutputVO(RespStatusEnums.DATA_FORMATTING_ERROR.getCode(),
                RespStatusEnums.DATA_FORMATTING_ERROR.getMessage() + ", " + ex.getMessage());

    }

    /**
     * 非法输入或断言错误
     *
     * @param ex 异常
     * @return 返回值
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public OutputVO illegalArgumentException(IllegalArgumentException ex) {
        logger.error("非法输入或断言错误：【{}】", ex.getMessage());
        return new OutputVO(RespStatusEnums.Illegal_input_or_assertion_error.getCode(),
                RespStatusEnums.Illegal_input_or_assertion_error.getMessage() + ", " + ex.getMessage());
    }


    /**
     * 操作数据库出现异常
     *
     * @param ex 异常
     * @return 返回值
     */
    @ExceptionHandler(value = DataAccessException.class)
    public OutputVO dataDoException(DataAccessException ex) {
        logger.error("操作数据库出现异常：【{}】", ex.getMessage());
        return new OutputVO(RespStatusEnums.OPERATION_DATABASE_ERROR.getCode(),
                RespStatusEnums.OPERATION_DATABASE_ERROR.getMessage() + ", " + ex.getMessage());
    }


    /**
     * 字段校验不通过异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public OutputVO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringJoiner sj = new StringJoiner(";");
        ex.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return new OutputVO(RespStatusEnums.PARAMETER_ERROR.getCode(),
                RespStatusEnums.PARAMETER_ERROR.getMessage() + ", " + sj.toString());
    }



    /**
     * Validated 参数校验失败全局拦截处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public OutputVO handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return new OutputVO<>(RespStatusEnums.PARAMETER_ERROR.getCode(),
                RespStatusEnums.PARAMETER_ERROR.getMessage() + ", " + message);
    }


    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        return "参数验证失败";
    }

    /**
     * 拦截 sa-token 校验异常
     *
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
        logger.error("Request Exception Path:{}", request.getRequestURL());
        logger.error("GlobleExceptionHandler NotLoginException Exception:{}", ex.getMessage());
        //处理返回的错误信息
        return new OutputVO<>(RespStatusEnums.UNKNOWN_ERROR.getCode(), ex.getMessage(), AudioConst.error_1);

    }

    /**
     * 处理实体字段校验不通过异常
     *
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


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    static class ArgumentInvalid {
        /**
         * 字段
         */
        private String field;
        /**
         * 字段值
         */
        private Object rejectedValue;
        /**
         * 默认值
         */
        private String defaultMessage;
    }
}
