package com.cafe.gateway.manage.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.manage.property
 * @Author: zhouboyi
 * @Date: 2022/5/11 0:58
 * @Description: 获取 application 中定义的白名单 URL
 */
@Component
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreUrlsProperties {

    /**
     * 白名单 URL 列表
     */
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
