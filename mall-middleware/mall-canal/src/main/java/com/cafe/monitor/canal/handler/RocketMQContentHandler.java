package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.constant.rocketmq.RocketMQTopic;
import com.cafe.common.message.rocketmq.producer.RocketMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/28 16:45
 * @Description: RocketMQ 消息内容处理器
 */
@Component
public class RocketMQContentHandler {

    private final MessageContentHandler messageContentHandler;

    private final RocketMQProducer rocketMQProducer;

    @Autowired
    public RocketMQContentHandler(
        MessageContentHandler messageContentHandler,
        RocketMQProducer rocketMQProducer
    ) {
        this.messageContentHandler = messageContentHandler;
        this.rocketMQProducer = rocketMQProducer;
    }

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 组装消息
        Map<String, Object> content = messageContentHandler.handle(rowDataList, eventType);
        // 发送消息到 RocketMQ
        rocketMQProducer.convertAndSend(RocketMQTopic.TOPIC_MAP.get(RocketMQConstant.CANAL, tableName), content);
    }
}
