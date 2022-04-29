package com.cafe.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe
 * @Author: zhouboyi
 * @Date: 2022/4/25 14:16
 * @Description:
 */
@SpringCloudApplication
@ComponentScan("com.cafe")
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
