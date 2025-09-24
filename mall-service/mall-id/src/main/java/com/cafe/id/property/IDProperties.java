package com.cafe.id.property;

import com.cafe.id.support.IDGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    /**
     * 工作机器ID (全局配置)
     */
    private Long workerId;

    /**
     * 数据中心ID (全局配置)
     */
    private Long datacenterId;

    private Snowflake snowflake;

    private Redis redis;

    private Redisson redisson;

    private Jedis jedis;

    private Lettuce lettuce;

    /**
     * 分布式ID生成器配置抽象类
     */
    @Getter
    @Setter
    private static abstract class IDWorker {

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
     * Snowflake 分布式ID生成器配置
     */
    public static class Snowflake extends IDWorker {

    }

    /**
     * Redis 分布式ID生成器配置
     */
    public static class Redis extends IDWorker {

    }

    /**
     * Redisson 分布式ID生成器配置
     */
    public static class Redisson extends IDWorker {

    }

    /**
     * Jedis 分布式ID生成器配置
     */
    public static class Jedis extends IDWorker {

    }

    /**
     * Lettuce 分布式ID生成器配置
     */
    public static class Lettuce extends IDWorker {

    }

    /**
     * 初始化默认配置
     */
    @PostConstruct
    public void init() {
        initIDWorker(this::getSnowflake, this::setSnowflake, Snowflake::new);
        initIDWorker(this::getRedis, this::setRedis, Redis::new);
        initIDWorker(this::getRedisson, this::setRedisson, Redisson::new);
        initIDWorker(this::getJedis, this::setJedis, Jedis::new);
        initIDWorker(this::getLettuce, this::setLettuce, Lettuce::new);
    }

    /**
     * 初始化分布式ID生成器默认配置
     *
     * @param getter      分布式ID生成器 getter 方法
     * @param setter      分布式ID生成器 setter 方法
     * @param constructor 分布式ID生成器构造方法
     * @param <T>         分布式ID生成器类型
     */
    private <T extends IDWorker> void initIDWorker(Supplier<T> getter, Consumer<T> setter, Supplier<T> constructor) {
        // 如果分布式ID生成器配置为空, 创建分布式ID生成器配置
        if (Objects.isNull(getter.get())) {
            setter.accept(constructor.get());
        }
        // 如果分布式ID生成器的工作机器ID为空, 使用全局工作机器ID配置
        if (Objects.isNull(getter.get().getWorkerId())) {
            getter.get().setWorkerId(this.workerId);
        }
        // 如果分布式ID生成器的数据中心ID为空, 使用全局数据中心ID配置
        if (Objects.isNull(getter.get().getDatacenterId())) {
            getter.get().setDatacenterId(this.datacenterId);
        }
    }
}
