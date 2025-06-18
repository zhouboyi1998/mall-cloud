package com.cafe.infrastructure.elasticsearch.config;

import com.cafe.infrastructure.elasticsearch.property.OpenSearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.config
 * @Author: zhouboyi
 * @Date: 2025/6/15 6:15
 * @Description: OpenSearch Client 自动装配配置类
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(value = OpenSearchProperties.class)
@ConditionalOnClass(value = RestHighLevelClient.class)
public class OpenSearchClientAutoConfiguration {

    private final OpenSearchProperties openSearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(openSearchProperties.getUsername(), openSearchProperties.getPassword()));
        return new RestHighLevelClient(RestClient.builder(new HttpHost(openSearchProperties.getHost(), openSearchProperties.getPort(), openSearchProperties.getScheme()))
            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
    }
}
