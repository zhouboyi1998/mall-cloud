package com.cafe.ordercenter;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter
 * @Author: zhouboyi
 * @Date: 2023/10/24 16:58
 * @Description: 订单中心启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {FeignConstant.Client.ID, FeignConstant.Client.GOODS, FeignConstant.Client.STORAGE, FeignConstant.Client.ORDER, FeignConstant.Client.FOUNDATION, FeignConstant.Client.MEMBER, FeignConstant.Client.REVIEW})
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class OrderCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterApplication.class, args);
    }
}
