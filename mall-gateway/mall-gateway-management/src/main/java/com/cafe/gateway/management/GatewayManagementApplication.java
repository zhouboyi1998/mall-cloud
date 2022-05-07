package com.cafe.gateway.management;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway
 * @Author: zhouboyi
 * @Date: 2022/4/26 10:55
 * @Description: SpringCloud Gateway 后台管理系统网关启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
public class GatewayManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayManagementApplication.class, args);
    }
}