package com.cafe.opensearch.config;

import com.cafe.opensearch.property.OpenSearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch.config
 * @Author: zhouboyi
 * @Date: 2025/6/15 6:15
 * @Description: OpenSearch 配置类
 */
@RequiredArgsConstructor
@Configuration
public class OpenSearchConfig {

    private final OpenSearchProperties openSearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(openSearchProperties.getUsername(), openSearchProperties.getPassword()));
        return new RestHighLevelClient(RestClient.builder(new HttpHost(openSearchProperties.getHost(), openSearchProperties.getPort(), openSearchProperties.getProtocol()))
            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
    }
}
