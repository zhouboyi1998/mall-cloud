package com.cafe.debezium.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.debezium.property
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:25
 * @Description: Debezium 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "debezium")
public class DebeziumProperties {

    /**
     * 监听器唯一标识
     */
    private String name;

    /**
     * 偏移量配置
     */
    private Offset offset;

    /**
     * 变更历史配置
     */
    private History history;

    /**
     * 策略配置
     */
    private Strategy strategy;

    /**
     * 数据源配置
     */
    private Datasource datasource;

    @Getter
    @Setter
    public static class Offset {

        /**
         * 偏移量持久化处理器
         */
        private String handler;

        /**
         * 偏移量持久化文件
         */
        private String filename;

        /**
         * 偏移量记录周期 (单位: 毫秒)
         */
        private String flushInterval;
    }

    @Getter
    @Setter
    public static class History {

        /**
         * 数据库变更历史持久化处理器
         */
        private String handler;

        /**
         * 数据库变更历史持久化文件
         */
        private String filename;
    }

    @Getter
    @Setter
    public static class Strategy {

        /**
         * 是否监听表结构的变化
         */
        private String schemaChange;

        /**
         * MySQL 服务端唯一标识
         */
        private String serverId;

        /**
         * MySQL 主从同步中的逻辑名称
         */
        private String serverName;
    }

    @Getter
    @Setter
    public static class Datasource {

        /**
         * 主机名
         */
        private String host;

        /**
         * 端口
         */
        private String port;

        /**
         * 用户名
         */
        private String username;

        /**
         * 密码
         */
        private String password;

        /**
         * 监听的数据库
         */
        private String database;
    }
}
