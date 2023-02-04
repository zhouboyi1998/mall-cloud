package com.cafe.id.config;

import com.cafe.common.core.util.Snowflake;
import com.cafe.id.property.SnowflakeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.config
 * @Author: zhouboyi
 * @Date: 2022/12/6 10:47
 * @Description: Snowflake 配置
 */
@Configuration
public class SnowflakeConfig {

    private final SnowflakeProperties snowflakeProperties;

    @Autowired
    public SnowflakeConfig(SnowflakeProperties snowflakeProperties) {
        this.snowflakeProperties = snowflakeProperties;
    }

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(snowflakeProperties.getWorkerId(), snowflakeProperties.getDatacenterId());
    }
}
