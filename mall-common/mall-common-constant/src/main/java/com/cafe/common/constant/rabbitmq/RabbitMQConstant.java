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
         * binlog 交换机 --> role-menu 队列
         */
        public static final String BINLOG_TO_ROLE_MENU = "binlog-to-role-menu";

        /**
         * canal 交换机 --> role-menu 队列
         */
        public static final String CANAL_TO_ROLE_MENU = "canal-to-role-menu";

        /**
         * Exchange + TableName 作为组合键, 获取 RoutingKey
         */
        public static final MultiKeyMap<String, String> MAP = new MultiKeyMap<>();

        static {
            MAP.put(Exchange.BINLOG, MySQLConstant.Table.ROLE_MENU, BINLOG_TO_ROLE_MENU);
            MAP.put(Exchange.CANAL, MySQLConstant.Table.ROLE_MENU, CANAL_TO_ROLE_MENU);
        }
    }

    /**
     * 队列
     */
    public static class Queue {

        /**
         * role-menu 队列
         */
        public static final String ROLE_MENU = "role-menu";
    }
}
