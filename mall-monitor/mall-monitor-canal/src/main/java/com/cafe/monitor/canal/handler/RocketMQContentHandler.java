package com.cafe.monitor.canal.handler;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import com.cafe.goods.constant.GoodsTable;
import com.cafe.monitor.canal.util.ConvertUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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
        if (ObjectUtil.equal(GoodsTable.SKU, tableName)) {
            handleSku(tableName, rowDataList, eventType);
        } else if (GoodsTable.GOODS_RELATION.contains(tableName)) {
            handleGoodsRelation(tableName, rowDataList, eventType);
        }
    }

    private void handleSku(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {

    }

    private void handleGoodsRelation(String tableName, List<CanalEntry.RowData> rowDataList, CanalEntry.EventType eventType) {
        // 存储更新数据
        List<Map<String, Object>> updateDataList = new ArrayList<Map<String, Object>>();

        // 循环获取 RowChange 对象里的每一行数据
        for (CanalEntry.RowData rowData : rowDataList) {
            switch (eventType) {
                case UPDATE:
                    Map<String, Object> rowDataMap = new HashMap<String, Object>(2);
                    rowDataMap.put(MonitorConstant.BEFORE_DATA, ConvertUtil.convertToMap(rowData.getBeforeColumnsList()));
                    rowDataMap.put(MonitorConstant.AFTER_DATA, ConvertUtil.convertToMap(rowData.getAfterColumnsList()));
                    updateDataList.add(rowDataMap);
                    break;
                default:
                    break;
            }
        }

        // 组装最终的消息内容
        Map<String, Object> content = new HashMap<String, Object>(4);
        content.put(MonitorConstant.OPERATION, eventType.toString().toLowerCase());
        content.put(MonitorConstant.TABLE_NAME, tableName);
        content.put(MonitorConstant.DATA, updateDataList);

        // 发送消息到 RocketMQ
        rocketMQTemplate.convertAndSend(RocketMQTopic.CANAL_TO_ES_GOODS_RELATION, content);
    }
}
