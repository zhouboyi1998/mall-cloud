package com.cafe.meilisearch;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch
 * @Author: zhouboyi
 * @Date: 2025/6/2 1:34
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class MeiliSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeiliSearchApplication.class, args);
    }
}
