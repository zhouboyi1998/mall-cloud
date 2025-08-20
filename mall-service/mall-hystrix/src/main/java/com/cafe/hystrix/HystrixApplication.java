package com.cafe.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.hystrix
 * @Author: zhouboyi
 * @Date: 2025/8/20 16:52
 * @Description:
 */
@SpringCloudApplication
@EnableHystrixDashboard
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }
}
