package ai.dongsheng.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.common.Session;
import ai.dongsheng.exception.BaseException;

import ai.dongsheng.model.vo.InputVo;
import ai.dongsheng.model.vo.OutputVo;


@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@SuppressWarnings("unused")
public class HttpInterceptor{
    private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);
    @Resource
    private AuthorizationProvider authorizationProvider;

    @Pointcut("execution(public * ai.dongsheng.controller.app..*.*(..))")
	private void app(){}


	@Around("app()")
	public Object appAround(ProceedingJoinPoint joinPoint) {
		return verify(joinPoint);
	}




	private Object verify(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Object mv = null;
        try{
            Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
            if (method.isAnnotationPresent(Signature.class)) {
                Authorization a = authorizationProvider.verify(getHttpServletRequest().getHeader("Authorization"));
                Session.set(a.token);
            } else {
                Session.clear();
            }
            mv =  joinPoint.proceed();
        }
        catch (BaseException e){
            afterThrowing(joinPoint,e);
            mv = OutputVo.code(e.getCode(), e.getMessage());
        }
        catch (Throwable e) {
            afterThrowing(joinPoint, e);
            mv = OutputVo.code(ErrorCode.INTERNAL_SERVER_ERROR, "FAIL");
        }
        finally{
            logging(joinPoint,mv,System.currentTimeMillis() - startTime);
        }
        return mv;
    }

	private String getReqId(ProceedingJoinPoint joinPoint) {
	    if (joinPoint.getArgs().length > 0 && joinPoint.getArgs()[0] instanceof InputVo) {
	        return ((InputVo) joinPoint.getArgs()[0]).getReqId();
        }
        return null;
    }
	private HttpServletRequest getHttpServletRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	private HttpServletResponse getHttpServletResponse(){
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
	}
	
	private void afterThrowing(JoinPoint joinPoint, Throwable ex){
		try{
			String methodName = joinPoint.getSignature().getName();
			StringBuilder stringBuilder = new StringBuilder("接口[" + methodName + "] error:" + ex.getMessage()+"\n");	
			logger.error(stringBuilder.toString(),ex);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private void logging(JoinPoint joinPoint,Object retVal,long duration){
		try{
			String methodName = joinPoint.getSignature().getName();
			String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
			Object[] args = joinPoint.getArgs();

			StringBuilder strBuilder = new StringBuilder("method:" + methodName);
			strBuilder.append(",duration:"+duration+"ms");
			strBuilder.append(",ip:"+getHttpServletRequest().getHeader("X-Real-IP"));
			strBuilder.append(",url=\""+getHttpServletRequest().getRequestURL()+"\"");
			if(args.length > 0){
				strBuilder.append(",params={");
				for(int i = 0;i < args.length;i++){
					if(i != 0){
						strBuilder.append(",");
					}
					if(args[i] != null){
						strBuilder.append("'"+paramNames[i]+"':\"" + args[i].toString() + "\"");
					}
					else{
						strBuilder.append("'"+paramNames[i]+"':\"null\"");
					}
				}
				strBuilder.append("}");
			}
			if(retVal != null){
				strBuilder.append(",ret=" + retVal.toString());
			}
			strBuilder.append(",user-agent=\""+getHttpServletRequest().getHeader("User-Agent")+"\"");

			if(duration>=200) {
				logger.info(strBuilder.toString());
			}else {
				logger.info(strBuilder.toString());
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
