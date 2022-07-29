package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.message.rocketmq.constant.RocketMQProducer;
import com.cafe.goods.constant.GoodsTopicMap;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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

    private MessageContentHandler messageContentHandler;

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public RocketMQContentHandler(
        MessageContentHandler messageContentHandler,
        RocketMQTemplate rocketMQTemplate
    ) {
        this.messageContentHandler = messageContentHandler;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 组装消息
        Map<String, Object> content = messageContentHandler.handle(tableName, rowDataList, eventType);

        // 发送消息到 RocketMQ
        rocketMQTemplate.convertAndSend(GoodsTopicMap.TOPIC_MAP.get(RocketMQProducer.CANAL, tableName), content);
    }
}
