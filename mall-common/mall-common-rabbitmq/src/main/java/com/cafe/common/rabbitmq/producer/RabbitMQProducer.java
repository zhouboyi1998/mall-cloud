package com.cafe.common.rabbitmq.producer;

import com.cafe.common.util.json.JacksonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.rabbitmq.producer
 * @Author: zhouboyi
 * @Date: 2022/5/18 15:03
 * @Description: RabbitMQ 消息生产者
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMQProducer {

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
        log.info("RabbitMQProducer.convertAndSend(): rabbitmq message -> {}", message);
        // 发送消息到 RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
