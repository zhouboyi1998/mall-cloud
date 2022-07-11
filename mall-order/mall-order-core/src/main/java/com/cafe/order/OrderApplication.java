package com.cafe.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order
 * @Author: zhouboyi
 * @Date: 2022/5/6 9:34
 * @Description: 订单模块启动类
 */
@SpringCloudApplication
@EnableSwagger2WebMvc
@EnableFeignClients(basePackages = "com.cafe.goods.feign")
@ComponentScan(basePackages = "com.cafe")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
