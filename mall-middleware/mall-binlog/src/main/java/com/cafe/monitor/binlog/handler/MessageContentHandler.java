package com.cafe.monitor.binlog.handler;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.monitor.MonitorConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.rabbitmq.RabbitMQExchange;
import com.cafe.common.constant.rabbitmq.RabbitMQRoutingKeyMap;
import com.cafe.common.message.rabbitmq.producer.RabbitMQProducer;
import com.cafe.monitor.binlog.bean.TableBeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog.handler
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:01
 * @Description: 消息内容处理器
 */
@Component
public class MessageContentHandler {

    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public MessageContentHandler(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    /**
     * 将不带属性名的 Serializable 转换为带属性名的 Map
     *
     * @param beforeRowList 更新前的数据
     * @param afterRowList  更新后的数据
     * @param tableName     数据库表名 (database.table)
     * @param operation     数据变更的类型
     */
    public void handle(List<Serializable[]> beforeRowList, List<Serializable[]> afterRowList, String tableName, String operation) {
        // 组装最终的消息内容
        Map<String, Object> content = new HashMap<>(4);
        content.put(MonitorConstant.OPERATION, operation);

        // 根据表名获取 JavaBean 列名集合
        List<String> fieldNameList = getFieldNameList(tableName);

        // 处理更新前的数据
        List<Map<String, Object>> beforeDataList = handleRowList(beforeRowList, fieldNameList);
        content.put(MonitorConstant.BEFORE_DATA, beforeDataList);

        // 处理更新后的数据
        List<Map<String, Object>> afterDataList = handleRowList(afterRowList, fieldNameList);
        content.put(MonitorConstant.AFTER_DATA, afterDataList);

        // 发送消息到 RabbitMQ
        rabbitMQProducer.convertAndSend(
            RabbitMQExchange.BINLOG,
            RabbitMQRoutingKeyMap.ROUTING_KEY_MAP.get(RabbitMQExchange.BINLOG, tableName),
            content
        );
    }

    /**
     * 根据表名获取对应的 JavaBean 列名集合
     *
     * @param tableName
     * @return
     */
    private List<String> getFieldNameList(String tableName) {
        // 存储列名的字符串集合
        List<String> fieldNameList = new ArrayList<>();
        // 根据表名获取对应的 JavaBean 属性数组
        Field[] fields = TableBeanMap.TABLE_BEAN_MAP.get(tableName).getDeclaredFields();
        for (Field field : fields) {
            // 获取列名的最后一段 (去除列名的修饰符、类名前缀)
            fieldNameList.add(field.toString().substring(field.toString().lastIndexOf(StringConstant.POINT) + 1));
        }
        // 去除属性列表中的序列号属性
        fieldNameList.remove(FieldConstant.SERIAL_VERSION_UID);
        return fieldNameList;
    }

    /**
     * 处理更新的数据
     *
     * @param rowList
     * @return
     */
    private List<Map<String, Object>> handleRowList(List<Serializable[]> rowList, List<String> fieldNameList) {
        // 存储处理后的数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        // 循环处理每一行更新的数据
        for (Serializable[] row : rowList) {
            // 存储属性名与字段值对应关系的 Map
            Map<String, Object> rowMap = new HashMap<>(16);
            for (int i = 0; i < row.length; i++) {
                rowMap.put(fieldNameList.get(i), row[i]);
            }
            dataList.add(rowMap);
        }
        return dataList;
    }
}
