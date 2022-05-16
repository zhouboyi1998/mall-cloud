package com.cafe.monitor.binlog;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog
 * @Author: zhouboyi
 * @Date: 2022/5/16 15:06
 * @Description: MySQL 数据库监听模块启动类
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
public class MonitorBinlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorBinlogApplication.class, args);
    }
}
