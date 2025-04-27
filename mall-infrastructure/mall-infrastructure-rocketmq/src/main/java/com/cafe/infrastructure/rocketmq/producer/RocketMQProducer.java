package com.cafe.infrastructure.rocketmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.rocketmq.producer
 * @Author: zhouboyi
 * @Date: 2023/4/24 21:58
 * @Description: RocketMQ 消息生产者
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RocketMQProducer {

    private final RocketMQTemplate rocketMQTemplate;

    private final DefaultSendCallback defaultSendCallback;

    private final ObjectMapper objectMapper;

    /**
     * 发送消息到 RocketMQ
     *
     * @param topic   主题
     * @param content 消息内容
     */
    @SneakyThrows
    public <T> void convertAndSend(String topic, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String message = objectMapper.writeValueAsString(content);
        // 打印日志
        log.info("RocketMQProducer.convertAndSend(): rocketmq message -> {}", message);
        // 发送消息到 RocketMQ
        rocketMQTemplate.convertAndSend(topic, message);
    }

    /**
     * 异步有序发送消息到 RocketMQ
     *
     * @param topic   主题
     * @param content 消息内容
     * @param hashKey 消息哈希键
     */
    @SneakyThrows
    public <T> void asyncSendOrderly(String topic, T content, String hashKey) {
        // 将消息内容转换为 JSON 字符串格式
        String message = objectMapper.writeValueAsString(content);
        // 打印日志
        log.info("RocketMQProducer.asyncSendOrderly(): rocketmq message -> {}", message);
        // 发送消息到 RocketMQ
        rocketMQTemplate.asyncSendOrderly(topic, MessageBuilder.withPayload(message).build(), hashKey, defaultSendCallback);
    }
}
