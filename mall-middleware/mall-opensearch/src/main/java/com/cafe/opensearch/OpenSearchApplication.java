package com.cafe.opensearch;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch
 * @Author: zhouboyi
 * @Date: 2025/6/15 5:09
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class OpenSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenSearchApplication.class, args);
    }
}
