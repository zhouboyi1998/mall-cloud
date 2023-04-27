package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.rabbitmq.RabbitMQConstant;
import com.cafe.common.message.rabbitmq.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/15 9:52
 * @Description: RabbitMQ 消息内容处理器
 */
@Component
public class RabbitMQContentHandler {

    private final MessageContentHandler messageContentHandler;

    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public RabbitMQContentHandler(
        MessageContentHandler messageContentHandler,
        RabbitMQProducer rabbitMQProducer
    ) {
        this.messageContentHandler = messageContentHandler;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 组装消息
        Map<String, Object> content = messageContentHandler.handle(rowDataList, eventType);
        // 发送消息到 RabbitMQ
        String routingKey = RabbitMQConstant.RoutingKey.MAP.get(RabbitMQConstant.Exchange.CANAL, tableName);
        rabbitMQProducer.convertAndSend(RabbitMQConstant.Exchange.CANAL, routingKey, content);
    }
}
