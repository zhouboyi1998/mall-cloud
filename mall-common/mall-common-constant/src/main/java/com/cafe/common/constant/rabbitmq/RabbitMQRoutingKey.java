package com.cafe.common.constant.rabbitmq;

import com.cafe.common.constant.mysql.TableConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rabbitmq
 * @Author: zhouboyi
 * @Date: 2022/5/19 15:04
 * @Description: RabbitMQ 路由键
 */
public class RabbitMQRoutingKey {

    /**
     * Binlog 发送消息到 RoutingKey (角色-菜单关联数据)
     */
    public static final String BINLOG_SEND_ROLE_MENU = "binlog-send-role-menu";

    /**
     * Canal 发送消息到 RoutingKey (角色-菜单关联数据)
     */
    public static final String CANAL_SEND_ROLE_MENU = "canal-send-role-menu";

    /**
     * 使用 RabbitMQ Exchange、Database Table 作为组合键, 获取 RabbitMQ RoutingKey
     */
    public static final MultiKeyMap<String, String> ROUTING_KEY_MAP = new MultiKeyMap<>();

    static {
        ROUTING_KEY_MAP.put(RabbitMQExchange.BINLOG, TableConstant.ROLE_MENU, RabbitMQRoutingKey.BINLOG_SEND_ROLE_MENU);
        ROUTING_KEY_MAP.put(RabbitMQExchange.CANAL, TableConstant.ROLE_MENU, RabbitMQRoutingKey.CANAL_SEND_ROLE_MENU);
    }
}
