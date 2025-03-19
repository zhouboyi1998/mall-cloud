package com.cafe.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin
 * @Author: zhouboyi
 * @Date: 2023/6/21 10:23
 * @Description: Spring Boot Admin 监控启动类
 */
@SpringCloudApplication
@EnableAdminServer
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
