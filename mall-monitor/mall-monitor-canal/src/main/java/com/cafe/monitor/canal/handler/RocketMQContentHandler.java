package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/28 16:45
 * @Description: RocketMQ 消息内容处理器
 */
@Component
public class RocketMQContentHandler {

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public RocketMQContentHandler(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        System.out.println(tableName);
        System.out.println(rowDataList.toString());
        System.out.println(eventType.toString());

        rocketMQTemplate.convertAndSend(RocketMQTopic.CANAL_ELASTICSEARCH, tableName);
    }
}
