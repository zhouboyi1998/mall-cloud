package com.cafe.infrastructure.elasticsearch.config;

import com.cafe.infrastructure.elasticsearch.property.ElasticSearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.config
 * @Author: zhouboyi
 * @Date: 2022/7/27 10:55
 * @Description: ElasticSearch Client 自动装配配置类
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(value = ElasticSearchProperties.class)
@ConditionalOnClass(value = RestHighLevelClient.class)
public class ElasticSearchClientAutoConfiguration {

    private final ElasticSearchProperties elasticSearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(elasticSearchProperties.getHost(), elasticSearchProperties.getPort(), elasticSearchProperties.getScheme())));
    }
}
