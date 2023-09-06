package com.cafe.common.swagger.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.swagger.property
 * @Author: zhouboyi
 * @Date: 2022/9/23 16:28
 * @Description: Swagger 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档描述
     */
    private String description;
}
