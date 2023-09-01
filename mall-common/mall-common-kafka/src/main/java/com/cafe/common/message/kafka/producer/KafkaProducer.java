package com.cafe.common.message.kafka.producer;

import com.cafe.common.util.json.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.message.kafka.producer
 * @Author: zhouboyi
 * @Date: 2023/4/25 11:17
 * @Description: Kafka 消息生产者
 */
@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 发送消息到 Kafka
     *
     * @param topic   主题
     * @param content 消息内容
     */
    public <T> void send(String topic, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String message = JacksonUtil.writeValueAsString(content);
        // 打印日志
        LOGGER.info("KafkaProducer.send(): kafka message -> {}", message);
        // 发送消息到 Kafka
        kafkaTemplate.send(topic, message);
    }
}
