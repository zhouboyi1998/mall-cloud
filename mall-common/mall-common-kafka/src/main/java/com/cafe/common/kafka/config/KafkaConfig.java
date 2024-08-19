package com.cafe.common.kafka.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.kafka.config
 * @Author: zhouboyi
 * @Date: 2023/4/25 10:26
 * @Description: Kafka 配置类
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(value = {KafkaProperties.class})
@EnableKafka
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> producerProperties = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(producerProperties);
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
