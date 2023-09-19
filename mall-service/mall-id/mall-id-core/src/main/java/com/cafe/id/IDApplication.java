package com.cafe.id;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id
 * @Author: zhouboyi
 * @Date: 2022/10/31 16:54
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class IDApplication {

    public static void main(String[] args) {
        SpringApplication.run(IDApplication.class, args);
    }
}
