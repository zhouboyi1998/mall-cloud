package com.cafe.id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id
 * @Author: zhouboyi
 * @Date: 2022/10/31 16:54
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.cafe")
public class IDApplication {

    public static void main(String[] args) {
        SpringApplication.run(IDApplication.class, args);
    }
}
