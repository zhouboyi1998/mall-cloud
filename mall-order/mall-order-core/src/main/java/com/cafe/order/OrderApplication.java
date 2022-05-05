package com.cafe.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order
 * @Author: zhouboyi
 * @Date: 2022/5/6 9:34
 * @Description:
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.cafe.goods.feign")
@ComponentScan(basePackages = "com.cafe")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
