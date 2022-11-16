package com.cafe.common.constant.rabbitmq;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rabbitmq
 * @Author: zhouboyi
 * @Date: 2022/5/19 15:04
 * @Description: RabbitMQ 路由键
 */
public class RabbitMQRoutingKey {

    /**
     * Binlog 模块发送消息到角色表队列的路由键
     */
    public static final String BINLOG_TO_ROLE = "binlog-to-role";

    /**
     * Binlog 模块发送消息到角色-菜单表队列的路由键
     */
    public static final String BINLOG_TO_ROLE_MENU = "binlog-to-role-menu";

    /**
     * Canal 模块发送消息到角色表队列的路由键
     */
    public static final String CANAL_TO_ROLE = "canal-to-role";

    /**
     * Canal 模块发送消息到角色-菜单表队列的路由键
     */
    public static final String CANAL_TO_ROLE_MENU = "canal-to-role-menu";
}
