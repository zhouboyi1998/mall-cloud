package com.cafe.openapicenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.config
 * @Author: zhouboyi
 * @Date: 2024/12/27 16:09
 * @Description: WebClient 配置类
 */
@Configuration
public class WebClientConfig {

    /**
     * 通用 WebClient
     */
    @Primary
    @Bean(value = "webClient")
    public WebClient webClient() {
        return WebClient.create();
    }

    /**
     * 聚合数据开放平台 WebClient
     */
    @Bean(value = "webClient4Juhe")
    public WebClient webClient4Juhe() {
        return WebClient.builder()
            .baseUrl("https://apis.juhe.cn")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();
    }
}
