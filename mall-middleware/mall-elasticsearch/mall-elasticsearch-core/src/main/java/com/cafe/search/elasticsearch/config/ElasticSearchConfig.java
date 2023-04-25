package com.cafe.search.elasticsearch.config;

import com.cafe.search.elasticsearch.property.ElasticSearchProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.config
 * @Author: zhouboyi
 * @Date: 2022/7/27 10:55
 * @Description: ElasticSearch 配置类
 */
@Configuration
public class ElasticSearchConfig {

    private final ElasticSearchProperties elasticSearchProperties;

    @Autowired
    public ElasticSearchConfig(ElasticSearchProperties elasticSearchProperties) {
        this.elasticSearchProperties = elasticSearchProperties;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        HttpHost httpHost = new HttpHost(elasticSearchProperties.getHost(), elasticSearchProperties.getPort(), elasticSearchProperties.getSchema());
        return new RestHighLevelClient(RestClient.builder(httpHost));
    }
}
