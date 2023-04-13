package com.cafe.monitor.canal;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import com.cafe.common.constant.pool.BooleanConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal
 * @Author: zhouboyi
 * @Date: 2022/7/13 20:52
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients(basePackages = FeignConstant.FEIGN_CLIENT_PACKAGE_ID)
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class CanalApplication {

    public static void main(String[] args) {
        // 指定 RocketMQ 使用的日志框架
        System.setProperty(RocketMQConstant.SYSTEM_PROPERTY_ROCKETMQ_LOG, BooleanConstant.TRUE);
        new SpringApplicationBuilder(CanalApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
