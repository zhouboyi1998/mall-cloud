package com.cafe.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.canal.property.CanalProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/14 0:12
 * @Description: Canal 处理器入口
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CanalEntryHandler {

    private final CanalProperties canalProperties;

    private final RabbitMQContentHandler rabbitMQContentHandler;

    private final RocketMQContentHandler rocketMQContentHandler;

    /**
     * 获取 Canal Server 解析 Binlog 得到的信息
     *
     * @param entryList
     */
    @SneakyThrows
    public void handle(List<CanalEntry.Entry> entryList) {
        // 循环解析每一行变更的数据
        for (CanalEntry.Entry entry : entryList) {
            // 跳过事务相关的实体
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN ||
                entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            // 获取 RowChange, 其中包含了一行数据变更的所有特征
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            // 获取 Header
            CanalEntry.Header header = entry.getHeader();
            // 打印日志
            log.info("CanalEntryHandler.handle(): database -> {}, table -> {}, operation -> {}", header.getSchemaName(), header.getTableName(), header.getEventType());

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
