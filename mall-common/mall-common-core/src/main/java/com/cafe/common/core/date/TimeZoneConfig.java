package com.cafe.common.core.date;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.date
 * @Author: zhouboyi
 * @Date: 2023/3/23 16:30
 * @Description: 时区配置类
 */
@Configuration
public class TimeZoneConfig {

    /**
     * 默认时区
     *
     * @return
     */
    @Bean
    public ZoneId zoneId() {
        return ZoneId.of(AppConstant.DEFAULT_ZONE_ID);
    }

    /**
     * 默认时钟
     *
     * @return
     */
    @Bean
    public Clock clock() {
        return Clock.system(zoneId());
    }
}
