package com.cafe.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.swagger
 * @Author: zhouboyi
 * @Date: 2024/5/6 11:08
 * @Description: Swagger API 文档聚合模块启动类
 */
@SpringCloudApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
