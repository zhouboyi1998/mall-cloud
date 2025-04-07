package com.cafe.elasticsearch;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch
 * @Author: zhouboyi
 * @Date: 2022/7/27 10:44
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableElasticsearchRepositories(basePackages = "com.cafe.elasticsearch.repository")
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class ElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
