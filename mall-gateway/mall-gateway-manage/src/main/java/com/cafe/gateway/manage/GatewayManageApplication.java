package com.cafe.gateway.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.manage
 * @Author: zhouboyi
 * @Date: 2022/4/26 10:55
 * @Description: SpringCloud Gateway 后台管理系统网关启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
@EnableFeignClients(basePackages = "com.cafe.security.manage.feign")
public class GatewayManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayManageApplication.class, args);
    }
}
