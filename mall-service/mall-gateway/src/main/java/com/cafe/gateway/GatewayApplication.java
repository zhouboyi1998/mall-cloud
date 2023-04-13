package com.cafe.gateway;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway
 * @Author: zhouboyi
 * @Date: 2022/4/26 10:55
 * @Description: SpringCloud Gateway 网关启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
