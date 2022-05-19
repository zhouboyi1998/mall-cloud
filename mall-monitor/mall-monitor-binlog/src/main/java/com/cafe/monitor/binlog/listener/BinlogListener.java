package com.cafe.monitor.binlog.listener;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.common.constant.RabbitmqExchangeName;
import com.cafe.common.constant.RabbitmqRoutingKey;
import com.cafe.monitor.binlog.message.RabbitmqProducer;
import com.cafe.monitor.binlog.property.BinlogProperties;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog.listener
 * @Author: zhouboyi
 * @Date: 2022/5/16 19:48
 * @Description: Binlog 监听器
 */
@Component
public class BinlogListener implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinlogListener.class);

    private BinlogProperties binlogProperties;

    private RabbitmqProducer rabbitmqProducer;

    @Autowired
    public BinlogListener(
        BinlogProperties binlogProperties,
        RabbitmqProducer rabbitmqProducer
    ) {
        this.binlogProperties = binlogProperties;
        this.rabbitmqProducer = rabbitmqProducer;
    }

    @Override
    public void run(String... args) throws Exception {

        // 存储 TableId 和 TableName 的对应关系
        Map<Long, String> tableMap = new HashMap<Long, String>(8);

        // 创建 BinaryLog 客户端并初始化
        BinaryLogClient binaryLogClient = new BinaryLogClient(
            binlogProperties.getHost(),
            binlogProperties.getPort(),
            binlogProperties.getUsername(),
            binlogProperties.getPassword()
        );
        binaryLogClient.setServerId(binlogProperties.getServerId());

        // 开启事件监听
        binaryLogClient.registerEventListener((event -> {
            // 获取事件数据
            EventData eventData = event.getData();
            if (ObjectUtil.isNotNull(eventData)) {
                // 获取数据库表信息
                if (eventData instanceof TableMapEventData) {
                    TableMapEventData tableMapEventData = (TableMapEventData) eventData;
                    // 维护 TableId 和 TableName 的对应关系
                    tableMap.put(
                        tableMapEventData.getTableId(),
                        tableMapEventData.getDatabase() + "." + tableMapEventData.getTable()
                    );
                }
                // 监听 update 操作
                if (eventData instanceof UpdateRowsEventData) {
                    // 转换为 update 监听数据
                    UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(updateRowsEventData.getTableId());
                    // 判断是否为需要监听的表
                    if (ObjectUtil.isNotNull(tableName) && binlogProperties.getTable().contains(tableName)) {
                        // 打印日志
                        LOGGER.info("Update Operation TableName: " + tableName);
                        // 存储所有修改行的主键id
                        List<Long> ids = new ArrayList<Long>();
                        // 循环每一行修改的数据
                        for (Map.Entry<Serializable[], Serializable[]> row : updateRowsEventData.getRows()) {
                            // 获取该行数据的第一列, 即主键id
                            ids.add((Long) row.getValue()[0]);
                        }
                        // 发送消息到 RabbitMQ
                        rabbitmqProducer.convertAndSend(
                            RabbitmqExchangeName.BINLOG,
                            RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU_RELATION,
                            ids
                        );
                    }
                }
                // 监听 insert 操作
                else if (eventData instanceof WriteRowsEventData) {
                    // 转换为 insert 监听数据
                    WriteRowsEventData writeRowsEventData = (WriteRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(writeRowsEventData.getTableId());
                    // 判断是否为需要监听的表
                    if (ObjectUtil.isNotNull(tableName) && binlogProperties.getTable().contains(tableName)) {
                        // 打印日志
                        LOGGER.info("Insert Operation TableName: " + tableName);
                        // 存储所有新增行的主键id
                        List<Long> ids = new ArrayList<Long>();
                        // 循环每一行新增的数据
                        for (Serializable[] row : writeRowsEventData.getRows()) {
                            // 获取该行数据的第一列, 即主键id
                            ids.add((Long) row[0]);
                        }
                        // 发送消息到 RabbitMQ
                        rabbitmqProducer.convertAndSend(
                            RabbitmqExchangeName.BINLOG,
                            RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU_RELATION,
                            ids
                        );
                    }
                }
                //监听 delete 操作
                else if (eventData instanceof DeleteRowsEventData) {
                    // 转换为 delete 监听数据
                    DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(deleteRowsEventData.getTableId());
                    // 判断是否为需要监听的表
                    if (ObjectUtil.isNotNull(tableName) && binlogProperties.getTable().contains(tableName)) {
                        // 打印日志
                        LOGGER.info("Delete Operation TableName: " + tableName);
                        // 存储所有删除行的主键id
                        List<Long> ids = new ArrayList<Long>();
                        // 循环每一行删除的数据
                        for (Serializable[] row : deleteRowsEventData.getRows()) {
                            // 获取该行数据的第一列, 即主键id
                            ids.add((Long) row[0]);
                        }
                        // 发送消息到 RabbitMQ
                        rabbitmqProducer.convertAndSend(
                            RabbitmqExchangeName.BINLOG,
                            RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU_RELATION,
                            ids
                        );
                    }
                }
            }
        }));

        // 开启数据库连接
        try {
            binaryLogClient.connect();
            LOGGER.info("Listen on the database " + binlogProperties.getHost() + ":" + binlogProperties.getPort() + " successfully!");
        } catch (IOException e) {
            LOGGER.error("Database " + binlogProperties.getHost() + ":" + binlogProperties.getPort() + " connection error by {}", e.getMessage());
        }
    }
}
