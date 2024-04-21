package com.cafe.apiconsumer;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.apiconsumer
 * @Author: zhouboyi
 * @Date: 2024/4/21 4:39
 * @Description: 开放接口消费中心启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class APIConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(APIConsumerApplication.class, args);
    }
}
