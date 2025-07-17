package com.cafe.id.config;

import com.cafe.common.lang.algorithm.id.Snowflake;
import com.cafe.id.property.IDProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.config
 * @Author: zhouboyi
 * @Date: 2022/12/6 10:47
 * @Description: 分布式ID配置类
 */
@RequiredArgsConstructor
@Configuration
public class IDConfig {

    private final IDProperties IDProperties;

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(IDProperties.getSnowflake().getWorkerId(), IDProperties.getSnowflake().getDatacenterId());
    }
}
