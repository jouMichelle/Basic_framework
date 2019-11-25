package ai.dongsheng.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import ai.dongsheng.exception.BaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CotrollerException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();

        int code = 200;
        String msg = "";
        if (e instanceof BaseException) {
            BaseException be = (BaseException) e;
            code = be.getCode();
            msg = be.getMessage();
        } else {
            code = 500;
            msg = "服务器错误";
        }

        httpServletResponse.setStatus(code);
        httpServletResponse.setContentType(MediaType.TEXT_PLAIN_VALUE); //设置ContentType
        httpServletResponse.setCharacterEncoding("UTF-8"); //避免乱码
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            httpServletResponse.getWriter().write(msg);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return mv;
    }
}
