package com.cafe.storage;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage
 * @Author: zhouboyi
 * @Date: 2022/12/29 19:34
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = FeignConstant.Client.ID)
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}
