package com.cafe.common.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.kafka.producer
 * @Author: zhouboyi
 * @Date: 2023/4/25 11:17
 * @Description: Kafka 消息生产者
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    /**
     * 发送消息到 Kafka
     *
     * @param topic   主题
     * @param content 消息内容
     */
    @SneakyThrows
    public <T> void send(String topic, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String message = objectMapper.writeValueAsString(content);
        // 打印日志
        log.info("KafkaProducer.send(): kafka message -> {}", message);
        // 发送消息到 Kafka
        kafkaTemplate.send(topic, message);
    }
}
