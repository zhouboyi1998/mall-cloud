package com.cafe.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.rocketmq.producer.RocketMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/28 16:45
 * @Description: RocketMQ 消息内容处理器
 */
@RequiredArgsConstructor
@Component
public class RocketMQContentHandler {

    private final MessageContentHandler messageContentHandler;

    private final RocketMQProducer rocketMQProducer;

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 组装消息
        Map<String, Object> content = messageContentHandler.handle(rowDataList, eventType);
        // 发送消息到 RocketMQ
        rocketMQProducer.convertAndSend(RocketMQConstant.Topic.MAP.get(RocketMQConstant.Producer.CANAL, tableName), content);
    }
}
