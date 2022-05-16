package com.cafe.monitor.mysql.listener;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.monitor.mysql.property.BinlogProperties;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.mysql.listener
 * @Author: zhouboyi
 * @Date: 2022/5/16 19:48
 * @Description:
 */
@Component
public class BinlogListener implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinlogListener.class);

    private BinlogProperties binlogProperties;

    @Autowired
    public BinlogListener(BinlogProperties binlogProperties) {
        this.binlogProperties = binlogProperties;
    }

    @Override
    public void run(String... args) throws Exception {

        // 新建 Map 存储 Table 信息
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
                    tableMap.put(tableMapEventData.getTableId(),
                        tableMapEventData.getDatabase() + "." + tableMapEventData.getTable()
                    );
                    System.out.println(tableMap.get(tableMapEventData.getTableId()));
                }
                // 监听 update 操作
                if (eventData instanceof UpdateRowsEventData) {
                    // 获取 update 监听数据
                    UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(updateRowsEventData.getTableId());
                    // 打印日志
                    LOGGER.info("TableName: " + tableName);
                    if (ObjectUtil.isNotNull(tableName) && binlogProperties.getTableName().contains(tableName)) {

                    }
                }
                // 监听 insert 操作
                else if (eventData instanceof WriteRowsEventData) {
                    // 获取 insert 监听数据
                    WriteRowsEventData writeRowsEventData = (WriteRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(writeRowsEventData.getTableId());
                    // 打印日志
                    LOGGER.info("TableName: " + tableName);
                    if (ObjectUtil.isNotNull(tableName) && binlogProperties.getTableName().contains(tableName)) {

                    }
                }
                //监听 delete 操作
                else if (eventData instanceof DeleteRowsEventData) {
                    // 获取 delete 监听数据
                    DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(deleteRowsEventData.getTableId());
                    // 打印日志
                    LOGGER.info("TableName: " + tableName);
                    if (ObjectUtil.isNotNull(tableName) && binlogProperties.getTableName().contains(tableName)) {

                    }
                }
            }
        }));

        // 开启数据库连接
        try {
            binaryLogClient.connect();
            LOGGER.info("Listen on the database successfully!");
        } catch (IOException e) {
            LOGGER.error("Database connection error by {}", e.getMessage());
        }
    }
}
