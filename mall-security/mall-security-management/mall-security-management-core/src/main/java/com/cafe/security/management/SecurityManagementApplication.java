package com.cafe.security.management;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management
 * @Author: zhouboyi
 * @Date: 2022/5/6 10:54
 * @Description:
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
@EnableFeignClients(basePackages = "com.cafe.admin.feign")
public class SecurityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityManagementApplication.class, args);
    }
}
