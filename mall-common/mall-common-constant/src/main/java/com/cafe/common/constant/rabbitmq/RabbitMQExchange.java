package com.cafe.common.constant.rabbitmq;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rabbitmq
 * @Author: zhouboyi
 * @Date: 2022/5/19 10:06
 * @Description: RabbitMQ 交换机名称
 */
public class RabbitMQExchange {

    /**
     * Binlog 模块使用的交换机
     */
    public static final String BINLOG = "binlog";

    /**
     * Canal 模块使用的交换机
     */
    public static final String CANAL = "canal";
}
