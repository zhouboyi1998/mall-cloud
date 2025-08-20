package com.cafe.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.turbine
 * @Author: zhouboyi
 * @Date: 2025/8/20 10:44
 * @Description:
 */
@SpringCloudApplication
@EnableTurbine
public class TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
