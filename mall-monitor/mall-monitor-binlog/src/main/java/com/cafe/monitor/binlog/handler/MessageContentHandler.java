package com.cafe.monitor.binlog.handler;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.admin.constant.AdminTableBeanMap;
import com.cafe.common.constant.RabbitmqExchange;
import com.cafe.common.message.rabbitmq.constant.ExchangeSourceRoutingMap;
import com.cafe.common.message.rabbitmq.producer.RabbitmqProducer;
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

    private RabbitmqProducer rabbitmqProducer;

    @Autowired
    public MessageContentHandler(RabbitmqProducer rabbitmqProducer) {
        this.rabbitmqProducer = rabbitmqProducer;
    }

    /**
     * 将不带属性名的 Serializable 转换为带属性名的 Map
     *
     * @param tableName
     * @param rowList
     */
    public void handle(String tableName, List<Serializable[]> rowList) {
        // 存储 JSONObject (每一行改动的数据) 的集合
        List<Map<String, Object>> rowMapList = new ArrayList<Map<String, Object>>();
        // 根据表名获取 JavaBean 列名集合
        List<String> fieldNameList = getFieldNameList(tableName);
        // 判断改动的行是否大于 0, 以及属性名数量与字段值数量是否相等 (属性中多了一个序列化号)
        if (rowList.size() > 0 && ObjectUtil.equal(fieldNameList.size() - 1, rowList.get(0).length)) {
            // 循环处理每一行改动过的数据
            for (Serializable[] row : rowList) {
                // 存储属性名与字段值对应关系的 Map
                Map<String, Object> rowMap = new HashMap<String, Object>(16);
                for (int i = 0; i < row.length; i++) {
                    rowMap.put(fieldNameList.get(i + 1), row[i]);
                }
                rowMapList.add(rowMap);
            }
        }
        // 发送消息到 RabbitMQ
        rabbitmqProducer.convertAndSend(
            RabbitmqExchange.BINLOG,
            ExchangeSourceRoutingMap.EXCHANGE_SOURCE_ROUTING_MAP.get(RabbitmqExchange.BINLOG, tableName),
            rowMapList
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
        List<String> fieldNameList = new ArrayList<String>();
        // 根据表名获取对应的 JavaBean 列名数组
        Field[] fields = AdminTableBeanMap.TABLE_BEAN_MAP.get(tableName).getDeclaredFields();
        for (Field field : fields) {
            // 获取列名的最后一段 (去除列名的修饰符、类名前缀)
            fieldNameList.add(field.toString().substring(field.toString().lastIndexOf(".") + 1));
        }
        return fieldNameList;
    }
}
