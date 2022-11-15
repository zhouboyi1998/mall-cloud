package com.cafe.common.swagger.config;

import com.cafe.common.swagger.property.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.swagger.config
 * @Author: zhouboyi
 * @Date: 2022/5/3 23:12
 * @Description: Swagger 配置类
 */
@Profile({"dev", "test"})
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    @Autowired
    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.cafe"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(swaggerProperties.getApplicationName())
            .description(swaggerProperties.getDescription())
            .contact(new Contact("zhouboyi", "https://github.com/zhouboyi1998", "1144188685@qq.com"))
            .termsOfServiceUrl("https://github.com/zhouboyi1998/mall-cloud")
            .version("version 1.0")
            .license("MIT")
            .licenseUrl("https://opensource.org/licenses/MIT")
            .build();
    }
}
