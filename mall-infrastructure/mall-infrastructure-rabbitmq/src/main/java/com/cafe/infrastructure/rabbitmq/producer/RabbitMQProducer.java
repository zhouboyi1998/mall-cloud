package com.cafe.infrastructure.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.rabbitmq.producer
 * @Author: zhouboyi
 * @Date: 2022/5/18 15:03
 * @Description: RabbitMQ 消息生产者
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    /**
     * 发送消息到 RabbitMQ
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容
     * @param <T>        消息内容类型
     */
    @SneakyThrows
    public <T> void convertAndSend(String exchange, String routingKey, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String message = objectMapper.writeValueAsString(content);
        // 打印日志
        log.info("RabbitMQProducer.convertAndSend(): rabbitmq message -> {}", message);
        // 发送消息到 RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    /**
     * 发送消息到 RabbitMQ
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容
     * @param <T>        消息内容类型
     * @param messageId  消息唯一ID
     */
    @SneakyThrows
    public <T> void convertAndSend(String exchange, String routingKey, T content, String messageId) {
        // 将消息内容转换为 JSON 字符串格式
        String message = objectMapper.writeValueAsString(content);
        // 新建消息关联数据对象, 存入消息唯一ID
        CorrelationData correlationData = new CorrelationData(messageId);
        // 打印日志
        log.info("RabbitMQProducer.convertAndSend(): rabbitmq messageId -> {}, message -> {}", messageId, message);
        // 发送消息到 RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
    }
}
