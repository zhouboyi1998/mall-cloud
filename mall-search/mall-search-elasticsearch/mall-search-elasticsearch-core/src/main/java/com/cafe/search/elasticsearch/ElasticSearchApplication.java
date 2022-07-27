package com.cafe.search.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch
 * @Author: zhouboyi
 * @Date: 2022/7/27 10:44
 * @Description:
 */
@SpringCloudApplication
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.cafe")
public class ElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
