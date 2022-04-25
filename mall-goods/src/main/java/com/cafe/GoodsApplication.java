package com.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Project: mall-cloud
 * @Package: com.cafe
 * @Author: zhouboyi
 * @Date: 2022/4/25 9:42
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
