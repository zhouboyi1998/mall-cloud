package com.cafe.elasticsearch.message.kafka;

import com.cafe.common.constant.kafka.KafkaConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.elasticsearch.index.OrderIndex;
import com.cafe.elasticsearch.service.OrderIndexService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.message.kafka
 * @Author: zhouboyi
 * @Date: 2024/6/28 10:02
 * @Description: Kafka 订单消息消费者
 */
@Component
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderIndexService orderIndexService;

    @Autowired
    public OrderConsumer(OrderIndexService orderIndexService) {
        this.orderIndexService = orderIndexService;
    }

    @KafkaListener(topics = {KafkaConstant.Topic.ORDER_INDEX}, groupId = KafkaConstant.ConsumerGroup.ELASTICSEARCH)
    public void listener(ConsumerRecord<String, String> record) {
        // 打印成功接收消息的日志
        LOGGER.info("OrderConsumer.listener(): kafka topic -> {}, offset -> {}, key -> {}, value -> {}", record.topic(), record.offset(), record.key(), record.value());
        // 获取消息内容
        OrderIndex orderIndex = JacksonUtil.readValue(record.value(), OrderIndex.class);
        try {
            // 存储订单索引
            orderIndexService.insert(orderIndex);
        } catch (IOException e) {
            LOGGER.error("OrderConsumer.listener(): Failed to update order index! error message -> {}", e.getMessage(), e);
        }
    }
}
