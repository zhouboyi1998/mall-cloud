package com.cafe.search.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.config
 * @Author: zhouboyi
 * @Date: 2022/7/27 10:55
 * @Description:
 */
@Configuration
public class ElasticSearchConfig {

    @Value(value = "${elastic-search.host}")
    private String host;

    @Value(value = "${elastic-search.port}")
    private Integer port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, "http")));
    }
}
