package com.cafe.elasticsearch.config;

import com.cafe.elasticsearch.property.ElasticSearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ElasticSearchConfig {

    private final ElasticSearchProperties elasticSearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 1. 创建认证凭据
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                org.apache.http.auth.AuthScope.ANY,
                new UsernamePasswordCredentials(
                        elasticSearchProperties.getUsername(),  // 从配置中获取用户名
                        elasticSearchProperties.getPassword()   // 从配置中获取密码
                )
        );

        // 2. 构建 HTTP 主机配置
        HttpHost httpHost = new HttpHost(
                elasticSearchProperties.getHost(),
                elasticSearchProperties.getPort(),
                elasticSearchProperties.getScheme()
        );

        // 3. 创建客户端并注入认证
        return new RestHighLevelClient(
                RestClient.builder(httpHost)
                        .setHttpClientConfigCallback(
                                httpClientBuilder -> httpClientBuilder
                                        .setDefaultCredentialsProvider(credentialsProvider)
                        )
        );
    }
}