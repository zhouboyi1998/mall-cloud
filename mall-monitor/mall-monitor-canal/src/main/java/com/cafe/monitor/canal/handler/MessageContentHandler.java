package com.cafe.monitor.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.MonitorConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.util
 * @Author: zhouboyi
 * @Date: 2022/7/29 15:58
 * @Description: 消息内容处理器
 */
@Component
public class MessageContentHandler {

    public Map<String, Object> handle(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 分别存储所有更新前数据和更新后数据
        List<Map<String, Object>> beforeDataList = new ArrayList<>();
        List<Map<String, Object>> afterDataList = new ArrayList<>();

        // 循环获取 RowChange 对象里的每一行数据
        for (CanalEntry.RowData rowData : rowDataList) {
            switch (eventType) {
                case UPDATE:
                    beforeDataList.add(convertToMap(rowData.getBeforeColumnsList()));
                    afterDataList.add(convertToMap(rowData.getAfterColumnsList()));
                    break;
                case INSERT:
                    afterDataList.add(convertToMap(rowData.getAfterColumnsList()));
                    break;
                case DELETE:
                    beforeDataList.add(convertToMap(rowData.getBeforeColumnsList()));
                    break;
                default:
                    break;
            }
        }

        // 组装最终的消息内容
        Map<String, Object> content = new HashMap<>(4);
        content.put(MonitorConstant.OPERATION, eventType.toString().toLowerCase());
        content.put(MonitorConstant.BEFORE_DATA, beforeDataList);
        content.put(MonitorConstant.AFTER_DATA, afterDataList);

        return content;
    }

    public Map<String, Object> convertToMap(List<CanalEntry.Column> columnList) {
        // 存储属性名与字段值对应关系的 Map
        Map<String, Object> rowMap = new HashMap<>(16);
        for (CanalEntry.Column column : columnList) {
            rowMap.put(column.getName(), column.getValue());
        }
        return rowMap;
    }
}
