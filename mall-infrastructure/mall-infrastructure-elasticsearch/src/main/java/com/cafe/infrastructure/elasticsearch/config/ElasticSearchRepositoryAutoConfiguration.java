package com.cafe.infrastructure.elasticsearch.config;

import com.cafe.infrastructure.elasticsearch.repository.support.SimpleExtensionElasticsearchRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.config
 * @Author: zhouboyi
 * @Date: 2025/6/18 22:36
 * @Description: ElasticSearch Repository 自动装配配置类
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cafe.**.repository", repositoryBaseClass = SimpleExtensionElasticsearchRepository.class)
public class ElasticSearchRepositoryAutoConfiguration {

}
