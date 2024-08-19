package com.cafe.common.rabbitmq.producer;

import com.cafe.common.util.json.JacksonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.rabbitmq.producer
 * @Author: zhouboyi
 * @Date: 2022/5/18 15:03
 * @Description: RabbitMQ 消息生产者
 */
@RequiredArgsConstructor
@Component
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到 RabbitMQ
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容
     */
    public <T> void convertAndSend(String exchange, String routingKey, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String message = JacksonUtil.writeValueAsString(content);
        // 打印日志
        LOGGER.info("RabbitMQProducer.convertAndSend(): rabbitmq message -> {}", message);
        // 发送消息到 RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
