package com.cafe.monitor.canal.handler;

import cn.hutool.json.JSONUtil;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.rocketmq.RocketMQProducer;
import com.cafe.common.constant.rocketmq.RocketMQTopicMap;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQContentHandler.class);

    private final MessageContentHandler messageContentHandler;

    private final RocketMQTemplate rocketMQTemplate;

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
        rocketMQTemplate.convertAndSend(RocketMQTopicMap.TOPIC_MAP.get(RocketMQProducer.CANAL, tableName), content);

        // 打印日志
        LOGGER.info("RocketMQContentHandler.handle(): Send RocketMQ Message -> {}", JSONUtil.toJsonStr(content));
    }
}
