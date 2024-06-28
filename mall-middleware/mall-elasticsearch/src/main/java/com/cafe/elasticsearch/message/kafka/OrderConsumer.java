package com.cafe.elasticsearch.message.kafka;

import com.cafe.common.constant.kafka.KafkaConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.elasticsearch.service.OrderService;
import com.cafe.order.vo.OrderVO;
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

    private final OrderService orderService;

    @Autowired
    public OrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = {KafkaConstant.Topic.ORDER_VO}, groupId = RocketMQConstant.ConsumerGroup.ELASTICSEARCH)
    public void listener(ConsumerRecord<String, String> record) {
        // 打印成功接收消息的日志
        LOGGER.info("OrderConsumer.listener(): kafka topic -> {}, offset -> {}, key -> {}, value -> {}", record.topic(), record.offset(), record.key(), record.value());
        // 获取消息内容
        OrderVO orderVO = JacksonUtil.readValue(record.value(), OrderVO.class);
        try {
            // 存储订单VO
            orderService.insert(orderVO);
        } catch (IOException e) {
            LOGGER.error("OrderConsumer.listener(): Failed to update order-vo index! error message -> {}", e.getMessage(), e);
        }
    }
}
