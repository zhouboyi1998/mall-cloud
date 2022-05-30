package com.cafe.monitor.binlog.database.listener;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.monitor.binlog.message.handler.MessageContentHandler;
import com.cafe.monitor.binlog.database.property.BinlogProperties;
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
 * @Package: com.cafe.monitor.binlog.database.listener
 * @Author: zhouboyi
 * @Date: 2022/5/16 19:48
 * @Description: Binlog 监听器
 */
@Component
public class BinlogListener implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinlogListener.class);

    private BinlogProperties binlogProperties;

    private MessageContentHandler messageContentHandler;

    @Autowired
    public BinlogListener(
        BinlogProperties binlogProperties,
        MessageContentHandler messageContentHandler
    ) {
        this.binlogProperties = binlogProperties;
        this.messageContentHandler = messageContentHandler;
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
                        LOGGER.info("Update Operation TableName: {}", tableName);
                        // 存储所有修改行的主键id
                        List<Serializable[]> beforeAndAfterRowList = new ArrayList<Serializable[]>();
                        // 循环每一行修改的数据
                        for (Map.Entry<Serializable[], Serializable[]> row : updateRowsEventData.getRows()) {
                            beforeAndAfterRowList.add(row.getKey());
                            beforeAndAfterRowList.add(row.getValue());
                        }
                        // 将数据交给消息内容处理器
                        messageContentHandler.handle(tableName, beforeAndAfterRowList);
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
                        LOGGER.info("Insert Operation TableName: {}", tableName);
                        // 将数据交给消息内容处理器
                        messageContentHandler.handle(tableName, writeRowsEventData.getRows());
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
                        LOGGER.info("Delete Operation TableName: {}", tableName);
                        // 将数据交给消息内容处理器
                        messageContentHandler.handle(tableName, deleteRowsEventData.getRows());
                    }
                }
            }
        }));

        // 开启数据库连接
        try {
            binaryLogClient.connect();
            LOGGER.info("BinlogListener.run() successful to listen on the database {}:{}",
                binlogProperties.getHost(), binlogProperties.getPort());
        } catch (IOException e) {
            LOGGER.error("BinlogListener.run() failed to listen on the database {}:{}, connection error by {}",
                binlogProperties.getHost(), binlogProperties.getPort(), e.getMessage());
        }
    }
}
