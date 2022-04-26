package com.cafe.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway
 * @Author: zhouboyi
 * @Date: 2022/4/26 10:55
 * @Description:
 */
@SpringCloudApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
