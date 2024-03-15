package com.cafe.binlog;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FeignConstant;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.binlog
 * @Author: zhouboyi
 * @Date: 2022/5/16 15:06
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients(basePackages = FeignConstant.Client.ID)
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class BinlogApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BinlogApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
