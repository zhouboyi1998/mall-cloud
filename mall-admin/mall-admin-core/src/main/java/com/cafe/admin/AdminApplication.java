package com.cafe.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin
 * @Author: zhouboyi
 * @Date: 2022/5/6 14:42
 * @Description: 管理员模块启动类
 */
@SpringCloudApplication
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.cafe")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
