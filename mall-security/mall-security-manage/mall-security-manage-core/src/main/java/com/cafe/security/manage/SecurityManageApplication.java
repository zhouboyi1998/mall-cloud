package com.cafe.security.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage
 * @Author: zhouboyi
 * @Date: 2022/5/6 10:54
 * @Description:
 */
@SpringCloudApplication
@EnableSwagger2WebMvc
@EnableFeignClients(basePackages = "com.cafe.admin.feign")
@ComponentScan(basePackages = "com.cafe")
public class SecurityManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityManageApplication.class, args);
    }
}
