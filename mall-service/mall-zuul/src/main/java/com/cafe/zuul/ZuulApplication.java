package com.cafe.zuul;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.zuul
 * @Author: zhouboyi
 * @Date: 2025/6/17 11:53
 * @Description: Zuul 网关启动类
 */
@SpringCloudApplication
@EnableZuulProxy
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
