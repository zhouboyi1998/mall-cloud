package com.cafe.common.message.rabbitmq.producer;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.message.rabbitmq.producer
 * @Author: zhouboyi
 * @Date: 2022/5/18 15:03
 * @Description: RabbitMQ 消息生产者
 */
@Component
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息到 RabbitMQ
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容
     */
    public <T> void convertAndSend(String exchange, String routingKey, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String message = JSONUtil.toJsonStr(content);
        // 打印日志
        LOGGER.info(message);
        // 发送消息到 RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, JSONUtil.toJsonStr(message));
    }
}
