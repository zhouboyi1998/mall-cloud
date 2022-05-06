package com.cafe.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin
 * @Author: zhouboyi
 * @Date: 2022/5/6 14:42
 * @Description: 管理员模块启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
