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

    public CanalEntryHandler(CanalProperties canalProperties) {
        this.canalProperties = canalProperties;
    }

    /**
     * 获取 Canal Server 解析 Binlog 得到的信息
     *
     * @param entryList
     */
    public void canalEntryHandle(List<CanalEntry.Entry> entryList) {
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

            // 获取数据库变更事件类型: insert / update / delete
            CanalEntry.EventType eventType = rowChange.getEventType();
            // 获取 Entry Header
            CanalEntry.Header header = entry.getHeader();

            // 判断是否为需要处理的表
            if (canalProperties.getTable().contains(header.getSchemaName() + "." + header.getTableName())) {
                // 循环获取 RowChange 对象里的每一行数据
                for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                    // 判断数据库变更事件类型
                    switch (eventType) {
                        case UPDATE:
                            printColumn(rowData.getBeforeColumnsList());
                            printColumn(rowData.getAfterColumnsList());
                            break;
                        case INSERT:
                            printColumn(rowData.getAfterColumnsList());
                            break;
                        case DELETE:
                            printColumn(rowData.getBeforeColumnsList());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * 打印该行每一列数据
     *
     * @param columnList
     */
    private void printColumn(List<CanalEntry.Column> columnList) {
        for (CanalEntry.Column column : columnList) {
            System.out.println(column.getName() + " : " + column.getValue() + " -- update=" + column.getUpdated());
        }
    }
}
