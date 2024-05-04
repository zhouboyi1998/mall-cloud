package com.cafe.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Project: mall-cloud
 * @Package: com.cafe
 * @Author: zhouboyi
 * @Date: 2022/4/24 15:57
 * @Description: Eureka 注册中心启动类
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
