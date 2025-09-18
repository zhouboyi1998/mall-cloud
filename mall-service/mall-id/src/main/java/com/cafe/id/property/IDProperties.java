package com.cafe.id.property;

import com.cafe.id.support.IDGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.property
 * @Author: zhouboyi
 * @Date: 2022/11/1 9:26
 * @Description: 分布式ID配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "id")
public class IDProperties {

    /**
     * 默认分布式ID生成器
     */
    private IDGenerator defaultGenerator = IDGenerator.SNOWFLAKE;

    private Snowflake snowflake;

    private Redis redis;

    private Redisson redisson;

    /**
     * 雪花分布式ID生成器配置
     */
    @Getter
    @Setter
    public static class Snowflake {

        /**
         * 工作机器ID
         */
        private Long workerId;

        /**
         * 数据中心ID
         */
        private Long datacenterId;
    }

    /**
     * Redis 分布式ID生成器配置
     */
    @Getter
    @Setter
    public static class Redis {

        /**
         * 工作机器ID
         */
        private Long workerId;

        /**
         * 数据中心ID
         */
        private Long datacenterId;
    }

    /**
     * Redisson 分布式ID生成器配置
     */
    @Getter
    @Setter
    public static class Redisson {

        /**
         * 工作机器ID
         */
        private Long workerId;

        /**
         * 数据中心ID
         */
        private Long datacenterId;
    }
}
