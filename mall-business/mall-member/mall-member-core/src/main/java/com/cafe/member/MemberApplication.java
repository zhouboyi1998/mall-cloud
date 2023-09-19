package com.cafe.member;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member
 * @Author: zhouboyi
 * @Date: 2022/5/6 14:42
 * @Description: 会员模块启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = FeignConstant.Client.ID)
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
