package com.cafe.id.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.property
 * @Author: zhouboyi
 * @Date: 2022/11/1 9:26
 * @Description: Snowflake 雪花算法配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeProperties {

    /**
     * 工作机器ID
     */
    private Long workerId;

    /**
     * 数据中心ID
     */
    private Long datacenterId;
}
