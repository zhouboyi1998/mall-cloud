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
    }

    /**
     * 路由键
     */
    public static class RoutingKey {

        /**
         * binlog 交换机和 role-resource 队列绑定的路由键
         */
        public static final String BINLOG_TO_ROLE_RESOURCE = "binlog-to-role-resource";

        /**
         * canal 交换机和 role-resource 队列绑定的路由键
         */
        public static final String CANAL_TO_ROLE_RESOURCE = "canal-to-role-resource";

        /**
         * Exchange + TableName 作为组合键, 获取 RoutingKey
         */
        public static final MultiKeyMap<String, String> MAP = new MultiKeyMap<>();

        static {
            MAP.put(Exchange.BINLOG, MySQLConstant.DatabaseTable.ROLE_RESOURCE, BINLOG_TO_ROLE_RESOURCE);
            MAP.put(Exchange.CANAL, MySQLConstant.DatabaseTable.ROLE_RESOURCE, CANAL_TO_ROLE_RESOURCE);
        }
    }

    /**
     * 队列
     */
    public static class Queue {

        /**
         * role-resource 队列
         */
        public static final String ROLE_RESOURCE = "role-resource";
    }
}
