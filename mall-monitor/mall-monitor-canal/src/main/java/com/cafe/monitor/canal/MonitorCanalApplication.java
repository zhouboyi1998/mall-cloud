package com.cafe.monitor.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal
 * @Author: zhouboyi
 * @Date: 2022/5/19 15:49
 * @Description: Canal 数据库监听启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
public class MonitorCanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorCanalApplication.class, args);
    }
}
