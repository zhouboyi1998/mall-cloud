package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.monitor.canal.property.CanalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.handler
 * @Author: zhouboyi
 * @Date: 2022/7/14 0:12
 * @Description: 消息内容处理器
 */
@Component
public class CanalEntryHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanalEntryHandler.class);

    private CanalProperties canalProperties;

    private MessageContentHandler messageContentHandler;

    public CanalEntryHandler(
        CanalProperties canalProperties,
        MessageContentHandler messageContentHandler
    ) {
        this.canalProperties = canalProperties;
        this.messageContentHandler = messageContentHandler;
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
            } catch (Exception e) {
                throw new RuntimeException("ERROR: Failed to parser from entry store value, entry: " + entry, e);
            }

            // 获取 Entry Header
            CanalEntry.Header header = entry.getHeader();
            // 打印日志
            LOGGER.info("Database: {}, Table: {}, Operation: {}",
                header.getSchemaName(), header.getTableName(), header.getEventType());

            // 判断是否为需要处理的表
            String tableName = header.getSchemaName() + "." + header.getTableName();
            if (canalProperties.getTable().contains(tableName)) {
                // 将数据交给消息内容处理器
                messageContentHandler.handle(tableName, rowChange.getRowDatasList(), rowChange.getEventType());
            }
        }
    }
}