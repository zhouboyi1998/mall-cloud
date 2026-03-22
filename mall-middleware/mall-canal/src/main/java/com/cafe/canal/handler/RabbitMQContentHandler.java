package com.cafe.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.rabbitmq.RabbitMQConstant;
import com.cafe.id.feign.IDFeign;
import com.cafe.infrastructure.rabbitmq.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private final IDFeign idFeign;

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 组装消息
        Map<String, Object> content = messageContentHandler.handle(rowDataList, eventType);
        // 生成消息唯一ID
        String messageId = Objects.requireNonNull(idFeign.nextId(null).getBody()).toString();
        // 发送消息到 RabbitMQ
        String routingKey = RabbitMQConstant.RoutingKey.MAP.get(RabbitMQConstant.Exchange.CANAL, tableName);
        rabbitMQProducer.convertAndSend(RabbitMQConstant.Exchange.CANAL, routingKey, content, messageId);
    }
}
