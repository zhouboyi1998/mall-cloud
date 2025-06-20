package com.cafe.grinder;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.grinder
 * @Author: zhouboyi
 * @Date: 2025/6/19 17:45
 * @Description: Grinder 网关启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class GrinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrinderApplication.class, args);
    }
}
