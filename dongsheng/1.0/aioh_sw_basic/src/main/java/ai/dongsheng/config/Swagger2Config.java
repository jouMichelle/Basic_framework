package ai.dongsheng.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

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
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("Authorization").description("token令牌")
                .modelRef(new ModelRef("string")).parameterType("header")
                //header中的ticket参数非必填，传空也可以
                .required(true).build();
        //根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.dongsheng.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 配置认证模式
     */
    private List<ApiKey> securitySchemes() {
        return newArrayList(new ApiKey("Authorization", "Authorization", "header"));
    }

    /**
     * 配置认证上下文
     */
    private List<SecurityContext> securityContexts() {
        return newArrayList(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build());
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("Authorization", "token令牌");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("Authorization", authorizationScopes));
    }

    /**
     * 项目信息
     */
    private ApiInfo apiInfo() {
        // API作者信息  作者   url         邮箱
        Contact contact = new Contact("MichelleJou", "https://blog.csdn.net/u014231523/article/details/76522486",
                "zhoubinbin@dongsheng.ai");
        return new ApiInfoBuilder()
                .title("基础系统（basic）")  // 标题
                .version("2.1") // 版本号
                .contact(contact) // 作者信息
                .build();
    }
}
