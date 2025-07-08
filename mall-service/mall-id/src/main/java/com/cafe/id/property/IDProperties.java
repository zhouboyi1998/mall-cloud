package com.cafe.id.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

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

    private Generator defaultGenerator = Generator.SNOWFLAKE;

    private Snowflake snowflake;

    private Redis redis;

    /**
     * 分布式ID生成器枚举
     */
    @AllArgsConstructor
    @Getter
    public enum Generator {

        /**
         * 雪花分布式ID生成器
         */
        SNOWFLAKE(GeneratorServiceName.SNOWFLAKE),

        /**
         * Redis分布式ID生成器
         */
        REDIS(GeneratorServiceName.REDIS),

        ;

        /**
         * 分布式ID生成器服务名称
         */
        private final String serviceName;

        /**
         * 根据枚举名称获取分布式ID生成器
         *
         * @param name 枚举名称
         * @return 分布式ID生成器
         */
        public static Generator getGeneratorByName(String name) {
            if (!StringUtils.hasText(name)) {
                return null;
            }
            name = name.toUpperCase();
            for (Generator generator : values()) {
                if (Objects.equals(generator.name(), name)) {
                    return generator;
                }
            }
            return null;
        }
    }

    /**
     * 分布式ID生成器服务名称
     */
    public static class GeneratorServiceName {

        public static final String SNOWFLAKE = "snowflakeIDServiceImpl";

        public static final String REDIS = "redisIDServiceImpl";
    }

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
}
