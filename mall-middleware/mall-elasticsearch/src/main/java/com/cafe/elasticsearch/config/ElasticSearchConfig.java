package com.cafe.elasticsearch.config;

import com.cafe.elasticsearch.property.ElasticSearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cafe.elasticsearch.repository")
@RequiredArgsConstructor
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    private final ElasticSearchProperties elasticSearchProperties;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create(ClientConfiguration.builder()
                        .connectedTo(elasticSearchProperties.getHost() + ":" + elasticSearchProperties.getPort())
                        .withBasicAuth(
                                elasticSearchProperties.getUsername(),
                                elasticSearchProperties.getPassword()
                        )
                        .build())
                .rest();
    }

    /**
     * 显式提供底层 RestClient 供健康检查使用
     */
    @Bean
    public RestClient elasticsearchRestClient() {
        return RestClient.builder(
                        new HttpHost(
                                elasticSearchProperties.getHost(),
                                elasticSearchProperties.getPort()
                        ))
                .setHttpClientConfigCallback(builder -> builder
                        .setDefaultCredentialsProvider(createCredentialsProvider())
                )
                .build();
    }

    private CredentialsProvider createCredentialsProvider() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(
                        elasticSearchProperties.getUsername(),
                        elasticSearchProperties.getPassword()
                ));
        return credentialsProvider;
    }
}