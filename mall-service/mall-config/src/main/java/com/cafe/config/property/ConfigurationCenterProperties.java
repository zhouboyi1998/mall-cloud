package com.cafe.config.property;

import com.cafe.config.environment.support.EnvironmentRepositoryType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.config.property
 * @Author: zhouboyi
 * @Date: 2025/10/23 16:13
 * @Description: 配置中心配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "configuration-center")
public class ConfigurationCenterProperties {

    /**
     * 配置环境仓库类型
     */
    private EnvironmentRepositoryType repository;

    /**
     * 本地仓库配置
     */
    private Local local;

    /**
     * Redis 仓库配置
     */
    private Redis redis;

    @Getter
    @Setter
    public static class Local {

        /**
         * 配置目录路径
         * <p>
         * 相对路径示例: ./docs/nacos/DEFAULT_GROUP
         * <p>
         * 绝对路径示例: /Users/zhouboyi/mall-cloud/docs/nacos/DEFAULT_GROUP
         */
        private String basePath;
    }

    @Getter
    @Setter
    public static class Redis {

        /**
         * 配置文件的 Redis Hash Key
         */
        private String hash;
    }
}
