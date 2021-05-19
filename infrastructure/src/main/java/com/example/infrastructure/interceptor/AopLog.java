package com.example.infrastructure.interceptor;

import eu.bitwalker.useragentutils.UserAgent;
import cn.hutool.json.JSONUtil;
import com.example.infrastructure.utils.http.RequestParamsToMap;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;

/**
 * @program: aiui
 * @description: 使用 aop 切面记录请求日志信息
 * @author:
 * @create: 2020-08-27 14:51
 **/
@Aspect
@Component
@Slf4j
public class AopLog {
    private static final String START_TIME = "request-start";

    @Resource
    private AuthorizationProvider authorizationProvider;

    /**
     * 切入点,以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.example.infrastructure.controller..*.*(..))")
    public void log() {

    }


    /**
     * 前置操作
     * 在切点之前织入
     *
     * @param point 切入点
     */
    @Before("log()")
    public void beforeLog(JoinPoint point) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("【请求 URL】：{}", request.getRequestURL());
        log.info("【请求 IP】：{}", request.getRemoteAddr());
        log.info("【请求类名】：{}，【请求方法名】：{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        String sn = request.getHeader("sn");
        String imei = request.getHeader("imei");
        String pattern = request.getHeader("pattern");
        log.info("【请求头 sn】:{} ,【请求头 pattern】:{},【请求头 imei】:{}",sn,pattern,imei);
        Map<String, Object> parameterMap = RequestParamsToMap.getParameterMap(request);
        String s = JSONUtil.toJsonStr(parameterMap);
        log.info("【请求参数】：{}，", s);
        Long start = System.currentTimeMillis();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        if (method.isAnnotationPresent(Signature.class)) {
            Signature annotation = method.getAnnotation(Signature.class);
            String value = annotation.value();
            if (value.equals("sn")) {
                // 如果加了注解代表要进行权限校验

            }else {
                // 如果加了注解代表要进行权限校验

            }

        }

        request.setAttribute(START_TIME, start);
    }

    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("【返回值】：{} \n\n", JSONUtil.toJsonStr(result));
        return result;
    }

    /**
     * 后置操作
     */
    @AfterReturning("log()")
    public void afterReturning() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("【请求耗时】：{}毫秒", end - start);
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        log.info("【浏览器类型】：{}，【操作系统】：{}，【原始User-Agent】：{}", userAgent.getBrowser().toString(),
                userAgent.getOperatingSystem().toString(), header);
    }
}
