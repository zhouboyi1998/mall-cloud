package com.cafe.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.config
 * @Author: zhouboyi
 * @Date: 2024/2/25 19:45
 * @Description: Spring Cloud Config 配置中心启动类
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
