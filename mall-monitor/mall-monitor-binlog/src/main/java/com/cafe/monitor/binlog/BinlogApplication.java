package com.cafe.monitor.binlog;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog
 * @Author: zhouboyi
 * @Date: 2022/5/16 15:06
 * @Description: MySQL Binlog 数据库监听模块启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.cafe")
public class BinlogApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BinlogApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
