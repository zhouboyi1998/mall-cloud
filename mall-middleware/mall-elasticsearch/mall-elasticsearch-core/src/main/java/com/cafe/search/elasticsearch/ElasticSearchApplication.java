package com.cafe.search.elasticsearch;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch
 * @Author: zhouboyi
 * @Date: 2022/7/27 10:44
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2WebMvc
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class ElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
