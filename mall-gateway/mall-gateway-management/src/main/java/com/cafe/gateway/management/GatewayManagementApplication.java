package com.cafe.gateway.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway
 * @Author: zhouboyi
 * @Date: 2022/4/26 10:55
 * @Description: SpringCloud Gateway 后台管理系统网关启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
@EnableFeignClients(basePackages = "com.cafe.security.management.feign")
public class GatewayManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayManagementApplication.class, args);
    }
}
