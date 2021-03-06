package ai.dongsheng.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan(basePackages={"ai.dongsheng"})
@ComponentScan(basePackages={"ai.dongsheng"})
@MapperScan({"ai.dongsheng.mapper"})
@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
@EnableAutoConfiguration
public class StarterApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(StarterApplication.class,args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(getClass());
	}
}
