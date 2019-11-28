package ai.dongsheng.exception;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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

import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.model.vo.OutputVo;

/**
 * @program: aioh_sw_im
 * @description: 全局异常类
 * @author: MichelleJou
 * @create: 2019-09-14 14:59
 **/
@SuppressWarnings("rawtypes")
@RestControllerAdvice
@Component
public class GlobleExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobleExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public OutputVo defultExcepitonHandler(HttpServletRequest httpServletRequest, Exception ex) {
        ex.printStackTrace();
        logger.info("GlobleExceptionHandler defultExcepitonHandler Exception:{} ", ex);
        if (ex instanceof BindException) {
            //处理返回的错误信息
            StringBuffer errorMsg = new StringBuffer();
            BindException c = (BindException) ex;
            List<ObjectError> errors = c.getBindingResult().getAllErrors();
            for (ObjectError error : errors) {
                errorMsg.append(error.getDefaultMessage()).append(";");
            }
            // return new BaseException(ErrorCode.ERROR_SIGN_NOT_NULL, "sign error not null");
            // return errorMsg.toString();
            return new OutputVo<>(ErrorCode.ERROR_PARAMETER, "parameter error" + errorMsg.toString());

        }

        return new OutputVo<>(ErrorCode.INTERNAL_SERVER_ERROR, "FAIL");

    }

    /**
     * 处理实体字段校验不通过异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public OutputVo validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : fieldErrors) {
            builder.append("   " + error.getDefaultMessage());
        }
        return new OutputVo<>(ErrorCode.ERROR_PARAMETER, "parameter error" + builder.toString());
    }

    /**
     * 处理@RequestParam校验不通过异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public OutputVo validationError(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> item : violations) {
            builder.append("   " + item.getMessage());
        }

        return new OutputVo<>(ErrorCode.ERROR_PARAMETER, "parameter error" + builder.toString());
    }

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public OutputVo bizExceptionHandler(HttpServletRequest req, BaseException e) {
        logger.error("发生业务异常！原因是：{}", e.getMessage());
        return new OutputVo<>(e.getCode(), e.getMessage());
    }


}
