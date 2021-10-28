package cn.codser.fmsapi.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 * http://127.0.0.1:8888/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                //.enable(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.codser.fmsapi.rest"))
                // 除去/fms/admin.*
                .paths(Predicates.not(PathSelectors.regex("/fms/admin.*")))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("fms-api-接口文档")
                //文档描述
                .description("fms-api-接口文档")
                //版本号
                .version("0.0.1")
                .build();
    }
}

