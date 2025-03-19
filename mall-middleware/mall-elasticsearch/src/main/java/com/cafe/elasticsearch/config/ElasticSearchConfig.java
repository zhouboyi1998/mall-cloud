package com.cafe.elasticsearch.config;

import com.cafe.elasticsearch.property.ElasticSearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.config
 * @Author: zhouboyi
 * @Date: 2022/7/27 10:55
 * @Description: ElasticSearch 配置类
 */
@RequiredArgsConstructor
@Configuration
public class ElasticSearchConfig {

    private final ElasticSearchProperties elasticSearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        HttpHost httpHost = new HttpHost(elasticSearchProperties.getHost(), elasticSearchProperties.getPort(), elasticSearchProperties.getScheme());
        return new RestHighLevelClient(RestClient.builder(httpHost));
    }
}
