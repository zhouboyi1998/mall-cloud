package com.cafe.goodscenter;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter
 * @Author: zhouboyi
 * @Date: 2024/7/31 23:01
 * @Description: 商品中心启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {FeignConstant.Client.ID, FeignConstant.Client.GOODS, FeignConstant.Client.ORDER, FeignConstant.Client.MERCHANT, FeignConstant.Client.REVIEW, FeignConstant.Client.ELASTICSEARCH})
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class GoodsCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsCenterApplication.class, args);
    }
}
