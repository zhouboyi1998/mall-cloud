package com.cafe.meilisearch.config;

import com.cafe.meilisearch.property.MeiliSearchProperties;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.config
 * @Author: zhouboyi
 * @Date: 2025/6/2 1:51
 * @Description: MeiliSearch 配置类
 */
@RequiredArgsConstructor
@Configuration
public class MeiliSearchConfig {

    private final MeiliSearchProperties meiliSearchProperties;

    @Bean
    public Config config() {
        return new Config(meiliSearchProperties.getHostUrl(), meiliSearchProperties.getApiKey());
    }

    @Bean
    public Client meiliSearchClient() {
        return new Client(config());
    }
}
