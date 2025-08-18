package com.cafe.common.constant.rabbitmq;

import com.cafe.common.constant.mysql.MySQLConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rabbitmq
 * @Author: zhouboyi
 * @Date: 2023/4/27 10:00
 * @Description: RabbitMQ 相关常量
 */
public class RabbitMQConstant {

    /**
     * 交换机
     */
    public static class Exchange {

        /**
         * binlog 交换机
         */
        public static final String BINLOG = "binlog";

        /**
         * canal 交换机
         */
        public static final String CANAL = "canal";

        /**
         * 死信交换机
         */
        public static final String DEAD_LETTER = "dead-letter";
    }

    /**
     * 路由键
     */
    public static class RoutingKey {

        /**
         * binlog 交换机和 binlog.role-resource 队列绑定的路由键
         */
        public static final String BINLOG_ROLE_RESOURCE = "binlog.role-resource";

        /**
         * canal 交换机和 canal.role-resource 队列绑定的路由键
         */
        public static final String CANAL_ROLE_RESOURCE = "canal.role-resource";

        /**
         * Exchange + TableName 作为组合键, 获取 RoutingKey
         */
        public static final MultiKeyMap<String, String> MAP = new MultiKeyMap<>();

        static {
            MAP.put(Exchange.BINLOG, MySQLConstant.DatabaseTable.ROLE_RESOURCE, BINLOG_ROLE_RESOURCE);
            MAP.put(Exchange.CANAL, MySQLConstant.DatabaseTable.ROLE_RESOURCE, CANAL_ROLE_RESOURCE);
        }
    }

    /**
     * 队列
     */
    public static class Queue {

        /**
         * binlog.role-resource 队列
         */
        public static final String BINLOG_ROLE_RESOURCE = "binlog.role-resource";

        /**
         * binlog.role-resource 死信队列
         */
        public static final String BINLOG_ROLE_RESOURCE_DEAD_LETTER = "binlog.role-resource.dead-letter";

        /**
         * canal.role-resource 队列
         */
        public static final String CANAL_ROLE_RESOURCE = "canal.role-resource";

        /**
         * canal.role-resource 死信队列
         */
        public static final String CANAL_ROLE_RESOURCE_DEAD_LETTER = "canal.role-resource.dead-letter";
    }

    /**
     * 参数名称
     */
    public static class ArgumentName {

        /**
         * 死信交换机
         */
        public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

        /**
         * 死信路由键
         */
        public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    }
}
