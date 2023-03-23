package com.cafe.common.core.config;

import com.cafe.common.constant.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.config
 * @Author: zhouboyi
 * @Date: 2023/3/23 16:30
 * @Description: 应用配置
 */
@Configuration
public class AppConfig {

    /**
     * 默认时区
     *
     * @return
     */
    @Bean
    public ZoneId zone() {
        return ZoneId.of(AppConstant.DEFAULT_ZONE_ID);
    }

    /**
     * 默认时钟
     *
     * @return
     */
    @Bean
    public Clock clock() {
        return Clock.system(zone());
    }
}
