package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.admin.constant.ExchangeSourceRoutingMap;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rabbitmq.constant.RabbitMQExchange;
import com.cafe.common.message.rabbitmq.producer.RabbitMQProducer;
import com.cafe.monitor.canal.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
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

    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    public RabbitMQContentHandler(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public void handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 分别存储所有更新前数据和更新后数据
        List<Map<String, Object>> beforeDataList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> afterDataList = new ArrayList<Map<String, Object>>();

        // 循环获取 RowChange 对象里的每一行数据
        for (CanalEntry.RowData rowData : rowDataList) {
            switch (eventType) {
                case UPDATE:
                    beforeDataList.add(ConvertUtil.convertToMap(rowData.getBeforeColumnsList()));
                    afterDataList.add(ConvertUtil.convertToMap(rowData.getAfterColumnsList()));
                    break;
                case INSERT:
                    afterDataList.add(ConvertUtil.convertToMap(rowData.getAfterColumnsList()));
                    break;
                case DELETE:
                    beforeDataList.add(ConvertUtil.convertToMap(rowData.getBeforeColumnsList()));
                    break;
                default:
                    break;
            }
        }

        // 组装最终的消息内容
        Map<String, Object> content = new HashMap<String, Object>(4);
        content.put(MonitorConstant.OPERATION, eventType.toString().toLowerCase());
        content.put(MonitorConstant.BEFORE_DATA, beforeDataList);
        content.put(MonitorConstant.AFTER_DATA, afterDataList);

        // 发送消息到 RabbitMQ
        rabbitMQProducer.convertAndSend(
            RabbitMQExchange.CANAL,
            ExchangeSourceRoutingMap.EXCHANGE_SOURCE_ROUTING_MAP.get(RabbitMQExchange.CANAL, tableName),
            content
        );
    }
}
