package com.cafe.monitor.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.mysql
 * @Author: zhouboyi
 * @Date: 2022/5/16 15:06
 * @Description: MySQL 数据库监听模块启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
public class MonitorMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorMysqlApplication.class, args);
    }
}
