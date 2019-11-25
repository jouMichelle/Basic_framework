package ai.dongsheng.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware{
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		setContext(context); 
	}
	
	public static ApplicationContext getContext(){
		return context;
	}
	
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
	public static Object getBean(String beanName, Object... argv){
		return context.getBean(beanName, argv);
	}
	
	public static <T> T getBean(String beanName,Class<T> clazz){
		return context.getBean(beanName, clazz);
	}

	public static void setContext(ApplicationContext context) {
		SpringContext.context = context;
	}
	
	/**
	 * 获取配置属性对应值
	 * @return
	 */
    public static String getProperty(String propName) {
    	return context.getEnvironment().getProperty(propName);
    }
    
	/**
	 * 获取当前激活的Profile
	 * @return
	 */
    public static String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }
    
}
