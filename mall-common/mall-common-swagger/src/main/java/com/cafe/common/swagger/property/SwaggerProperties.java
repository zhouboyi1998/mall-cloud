package com.cafe.common.swagger.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.swagger.property
 * @Author: zhouboyi
 * @Date: 2022/9/23 16:28
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * 微服务名称
     */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 微服务描述 (默认值: Microservice)
     *
     * @Value 注解获取配置的方式, 不允许配置为空, 找不到配置会报错
     * @ConfigurationProperties 注解允许配置为空, 找不到匹配的配置时, 也可以使用默认值
     */
    private String description = "Microservice";

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
