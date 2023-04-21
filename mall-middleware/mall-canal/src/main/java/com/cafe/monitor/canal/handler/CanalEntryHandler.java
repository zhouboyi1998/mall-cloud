package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.monitor.canal.property.CanalProperties;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/14 0:12
 * @Description: Canal 处理器入口
 */
@Component
public class CanalEntryHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanalEntryHandler.class);

    private final CanalProperties canalProperties;

    private final RabbitMQContentHandler rabbitMQContentHandler;

    private final RocketMQContentHandler rocketMQContentHandler;

    @Autowired
    public CanalEntryHandler(
        CanalProperties canalProperties,
        RabbitMQContentHandler rabbitMQContentHandler,
        RocketMQContentHandler rocketMQContentHandler
    ) {
        this.canalProperties = canalProperties;
        this.rabbitMQContentHandler = rabbitMQContentHandler;
        this.rocketMQContentHandler = rocketMQContentHandler;
    }

    /**
     * 获取 Canal Server 解析 Binlog 得到的信息
     *
     * @param entryList
     */
    public void handle(List<CanalEntry.Entry> entryList) {
        // 循环解析每一行变更的数据
        for (CanalEntry.Entry entry : entryList) {
            // 跳过事务相关的实体
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN ||
                entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            // 获取 RowChange 对象, 其中包含了一行数据变更的所有特征
            CanalEntry.RowChange rowChange;
            try {
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (InvalidProtocolBufferException e) {
                LOGGER.error("CanalEntryHandler.handler(): Failed to parser from entry store value, Entry -> {}, Exception -> {}", entry, e.getMessage());
                throw new RuntimeException("CanalEntryHandler.handler(): Failed to parser from entry store value, Entry -> " + entry, e);
            }

            // 获取 Entry Header
            CanalEntry.Header header = entry.getHeader();
            // 打印日志
            LOGGER.info("CanalEntryHandler.handler(): Database -> {}, Table -> {}, Operation -> {}",
                header.getSchemaName(), header.getTableName(), header.getEventType());

            // 判断是否为需要处理的表
            String tableName = header.getSchemaName() + "." + header.getTableName();
            if (canalProperties.getRabbitTable().contains(tableName)) {
                // 将数据交给 RabbitMQ 消息内容处理器
                rabbitMQContentHandler.handle(tableName, rowChange.getRowDatasList(), rowChange.getEventType());
            }
            if (canalProperties.getRocketTable().contains(tableName)) {
                // 将数据交给 RocketMQ 消息内容处理器
                rocketMQContentHandler.handle(tableName, rowChange.getRowDatasList(), rowChange.getEventType());
            }
        }
    }
}
