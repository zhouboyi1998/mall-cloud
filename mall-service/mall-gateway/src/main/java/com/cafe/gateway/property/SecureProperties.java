package com.cafe.gateway.property;

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
@Component
@ConfigurationProperties(prefix = "secure")
public class SecureProperties {

    /**
     * 白名单 URL 列表
     */
    private List<String> ignoreUrls;

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }
}
