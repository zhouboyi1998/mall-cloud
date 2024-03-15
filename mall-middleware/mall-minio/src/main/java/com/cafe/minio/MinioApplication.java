package com.cafe.minio;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:12
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = FeignConstant.Client.ID)
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class MinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class, args);
    }
}
