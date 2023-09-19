package com.cafe.common.swagger.config;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.swagger.SwaggerConstant;
import com.cafe.common.swagger.property.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
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
@Profile({AppConstant.DEV, AppConstant.TEST})
@Configuration
@EnableKnife4j
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
            .apis(RequestHandlerSelectors.basePackage(AppConstant.DEFAULT_PACKAGE))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(swaggerProperties.getTitle())
            .description(swaggerProperties.getDescription())
            .contact(new Contact(SwaggerConstant.CONTACT_NAME, SwaggerConstant.CONTACT_URL, SwaggerConstant.CONTACT_EMAIL))
            .termsOfServiceUrl(SwaggerConstant.TERMS_OF_SERVICE_URL)
            .version(SwaggerConstant.VERSION)
            .license(SwaggerConstant.LICENCE)
            .licenseUrl(SwaggerConstant.LICENCE_URL)
            .build();
    }
}
