package com.cafe.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.rabbitmq.RabbitMQConstant;
import com.cafe.common.rabbitmq.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/15 9:52
 * @Description: RabbitMQ 消息内容处理器
 */
@RequiredArgsConstructor
@Component
public class RabbitMQContentHandler {

    private final MessageContentHandler messageContentHandler;

    private final RabbitMQProducer rabbitMQProducer;

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 组装消息
        Map<String, Object> content = messageContentHandler.handle(rowDataList, eventType);
        // 发送消息到 RabbitMQ
        String routingKey = RabbitMQConstant.RoutingKey.MAP.get(RabbitMQConstant.Exchange.CANAL, tableName);
        rabbitMQProducer.convertAndSend(RabbitMQConstant.Exchange.CANAL, routingKey, content);
    }
}
