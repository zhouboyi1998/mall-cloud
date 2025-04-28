package com.cafe.security;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security
 * @Author: zhouboyi
 * @Date: 2022/5/6 10:54
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {FeignConstant.Client.CAPTCHA, FeignConstant.Client.USER})
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
