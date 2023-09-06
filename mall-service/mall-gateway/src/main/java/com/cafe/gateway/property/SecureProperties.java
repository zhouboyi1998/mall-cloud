package com.cafe.gateway.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.property
 * @Author: zhouboyi
 * @Date: 2022/5/11 0:58
 * @Description: 安全配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "secure")
public class SecureProperties {

    /**
     * 白名单 URL 列表
     */
    private List<String> ignoreUrls;
}
