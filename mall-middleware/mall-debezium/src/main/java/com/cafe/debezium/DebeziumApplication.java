package com.cafe.debezium;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.debezium
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:18
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class DebeziumApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebeziumApplication.class, args);
    }
}
