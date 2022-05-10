package com.cafe.gateway.management.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.management.config
 * @Author: zhouboyi
 * @Date: 2022/5/11 0:58
 * @Description:
 */
public class IgnoreUrlsConfig {

    @Value("${secure.ignore.urls}")
    private List<String> ignoreUrls;

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }
}
