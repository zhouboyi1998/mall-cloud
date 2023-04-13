package com.cafe.user;

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
 * @Package: com.cafe.user
 * @Author: zhouboyi
 * @Date: 2022/11/4 17:25
 * @Description: 用户模块启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2WebMvc
@EnableFeignClients(basePackages = FeignConstant.FEIGN_CLIENT_PACKAGE_ID)
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
