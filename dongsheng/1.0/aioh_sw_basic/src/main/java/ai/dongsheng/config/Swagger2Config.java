package ai.dongsheng.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: aioh_sw_pay
 * @description: swagger整合配置类
 * @author: MichelleJou
 * @create: 2019-11-20 09:43
 **/
@EnableSwagger2
@Configuration
@EnableSwaggerBootstrapUi
public class Swagger2Config {
    // Swagger 文档地址为：http://localhost:9099/swagger-ui.html
    // knife4j  文档地址为：http://127.0.0.1:9099/doc.html
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .genericModelSubstitutes(DeferredResult.class) //
                .useDefaultResponseMessages(false) //
                .forCodeGeneration(true) //
                .apiInfo(apiInfo()) //
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                // .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口
                .select() //
                .apis(RequestHandlerSelectors.basePackage("ai.dongsheng.controller")) //
                .paths(PathSelectors.any()) //
                .build(); //
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder() //
                .title("springboot利用swagger构建api文档") //
                .description("简单优雅的restfun风格") //
                .termsOfServiceUrl("https://github.com/springfox/springfox-demos") //
                .version("1.0") //
                .build();
    }
}
