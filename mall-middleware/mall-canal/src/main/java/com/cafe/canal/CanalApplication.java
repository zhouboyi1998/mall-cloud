package com.cafe.canal;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.canal
 * @Author: zhouboyi
 * @Date: 2022/7/13 20:52
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class CanalApplication {

    public static void main(String[] args) {
        // 指定 RocketMQ 使用的日志框架
        System.setProperty(RocketMQConstant.ROCKETMQ_CLIENT_LOG, StringConstant.TRUE);
        SpringApplication.run(CanalApplication.class, args);
    }
}
