package com.cafe.admin;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin
 * @Author: zhouboyi
 * @Date: 2022/5/6 14:42
 * @Description: 管理员模块启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2WebMvc
@EnableFeignClients(basePackages = FeignConstant.Client.ID)
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
