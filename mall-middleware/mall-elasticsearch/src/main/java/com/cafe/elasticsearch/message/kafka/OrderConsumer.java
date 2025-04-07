package com.cafe.elasticsearch.message.kafka;

import com.cafe.common.constant.kafka.KafkaConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.elasticsearch.model.index.OrderIndex;
import com.cafe.elasticsearch.service.OrderIndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.message.kafka
 * @Author: zhouboyi
 * @Date: 2024/6/28 10:02
 * @Description: Kafka 订单消息消费者
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private final OrderIndexService orderIndexService;

    @KafkaListener(topics = {KafkaConstant.Topic.ORDER_INDEX}, groupId = KafkaConstant.ConsumerGroup.ELASTICSEARCH)
    public void listener(ConsumerRecord<String, String> record) {
        // 打印成功接收消息的日志
        log.info("OrderConsumer.listener(): kafka topic -> {}, offset -> {}, key -> {}, value -> {}", record.topic(), record.offset(), record.key(), record.value());
        // 获取消息内容
        OrderIndex orderIndex = JacksonUtil.readValue(record.value(), OrderIndex.class);
        // 存储订单索引
        orderIndexService.save(orderIndex);
    }
}
