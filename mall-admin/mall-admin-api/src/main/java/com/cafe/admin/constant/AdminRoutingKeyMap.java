package com.cafe.admin.constant;

import com.cafe.common.message.rabbitmq.constant.RabbitMQExchange;
import com.cafe.common.message.rabbitmq.constant.RabbitMQRoutingKey;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.constant
 * @Author: zhouboyi
 * @Date: 2022/5/20 11:06
 * @Description: 交换机、消息标识、路由键对应关系, 通过前两者获取后者
 */
public class AdminRoutingKeyMap {

    public static final MultiKeyMap<String, String> ROUTING_KEY_MAP = new MultiKeyMap<String, String>() {{
        put(RabbitMQExchange.BINLOG, AdminTable.ROLE, RabbitMQRoutingKey.BINLOG_TO_ROLE);
        put(RabbitMQExchange.BINLOG, AdminTable.ROLE_MENU, RabbitMQRoutingKey.BINLOG_TO_ROLE_MENU);
        put(RabbitMQExchange.CANAL, AdminTable.ROLE, RabbitMQRoutingKey.CANAL_TO_ROLE);
        put(RabbitMQExchange.CANAL, AdminTable.ROLE_MENU, RabbitMQRoutingKey.CANAL_TO_ROLE_MENU);
    }};
}
