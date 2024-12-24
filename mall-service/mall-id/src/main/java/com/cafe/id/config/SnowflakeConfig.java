package com.cafe.id.config;

import com.cafe.common.lang.id.Snowflake;
import com.cafe.id.property.SnowflakeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.config
 * @Author: zhouboyi
 * @Date: 2022/12/6 10:47
 * @Description: Snowflake 配置类
 */
@RequiredArgsConstructor
@Configuration
public class SnowflakeConfig {

    private final SnowflakeProperties snowflakeProperties;

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(snowflakeProperties.getWorkerId(), snowflakeProperties.getDatacenterId());
    }
}
