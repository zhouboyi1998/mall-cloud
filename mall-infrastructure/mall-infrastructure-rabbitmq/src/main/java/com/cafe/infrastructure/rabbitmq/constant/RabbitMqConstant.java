package com.cafe.infrastructure.rabbitmq.constant;

public final class RabbitMqConstant {

    /**
     * 队列名称
     */
    public static final String   QUEUE_NAME = "mallQueue";

    /**
     * 交换机名称
     */
    public static final String   EXCHANGE_NAME = "mallExchanges";

    /**
     * 路由键名称
     */
    public static final String   ROUTING_KEY = "mallRoutingKey";

    /**
     * 死信队列名称
     */
    public static final String   DEAD_LETTER_QUEUE = "deadLetterQueue";

    /**
     * 死信交换机名称
     */
    public static final String   DEAD_LETTER_EXCHANGE = "deadLetterExchange";

    /**
     * 死信路由键名称
     */
    public static final String   DEAD_LETTER_ROUTING_KEY = "deadLetterRoutingKey";

    /**
     * 交换机
     */
    public static final String MY_EXCHANGE = "myExchange";

}