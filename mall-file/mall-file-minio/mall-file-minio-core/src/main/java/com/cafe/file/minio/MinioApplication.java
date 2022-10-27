package com.cafe.file.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:12
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.cafe")
public class MinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class, args);
    }
}