package com.cafe.common.constant.rabbitmq;

import com.cafe.common.constant.mysql.TableConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rabbitmq
 * @Author: zhouboyi
 * @Date: 2022/5/20 11:06
 * @Description: RabbitMQ 交换机、消息标识(数据库表名称)、RabbitMQ 路由键对应关系
 */
public class RabbitMQRoutingKeyMap {

    /**
     * 使用 RabbitMQ 交换机、消息标识(数据库表名称) 作为组合Key, 获取 RabbitMQ 路由键
     */
    public static final MultiKeyMap<String, String> ROUTING_KEY_MAP = new MultiKeyMap<String, String>() {{
        put(RabbitMQExchange.BINLOG, TableConstant.ROLE, RabbitMQRoutingKey.BINLOG_TO_ROLE);
        put(RabbitMQExchange.BINLOG, TableConstant.ROLE_MENU, RabbitMQRoutingKey.BINLOG_TO_ROLE_MENU);
        put(RabbitMQExchange.CANAL, TableConstant.ROLE, RabbitMQRoutingKey.CANAL_TO_ROLE);
        put(RabbitMQExchange.CANAL, TableConstant.ROLE_MENU, RabbitMQRoutingKey.CANAL_TO_ROLE_MENU);
    }};
}
