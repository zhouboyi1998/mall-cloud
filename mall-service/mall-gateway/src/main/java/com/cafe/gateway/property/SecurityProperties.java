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
 * @Description: 网关安全配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * 白名单 API
     */
    private List<String> whitelist;
}
