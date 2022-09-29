package com.cafe.monitor.canal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal
 * @Author: zhouboyi
 * @Date: 2022/7/13 20:52
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.cafe")
public class CanalApplication {

    public static void main(String[] args) {
        // 指定 RocketMQ 使用的日志框架
        System.setProperty("rocketmq.client.logUseSlf4j", "true");
        new SpringApplicationBuilder(CanalApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
