package com.cafe.elasticsearch.config;

import com.cafe.elasticsearch.property.ElasticSearchProperties;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Duration;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cafe.elasticsearch.repository")
@RequiredArgsConstructor
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    private final ElasticSearchProperties elasticSearchProperties;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        String host = elasticSearchProperties.getHost();
        Integer port = elasticSearchProperties.getPort();
        String scheme = elasticSearchProperties.getScheme();
        String username = elasticSearchProperties.getUsername();
        String password = elasticSearchProperties.getPassword();

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(host + ":" + port)
            .withConnectTimeout(Duration.ofSeconds(5))
            .withSocketTimeout(Duration.ofSeconds(3))
            .withBasicAuth(username, password)
            .build();

        return RestClients.create(clientConfiguration).rest();
    }
}