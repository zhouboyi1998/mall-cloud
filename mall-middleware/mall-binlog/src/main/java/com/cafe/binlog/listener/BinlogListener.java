package com.cafe.binlog.listener;

import com.cafe.binlog.handler.MessageContentHandler;
import com.cafe.binlog.property.BinlogProperties;
import com.cafe.common.constant.monitor.MonitorConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.binlog.listener
 * @Author: zhouboyi
 * @Date: 2022/5/16 19:48
 * @Description: Binlog 监听器
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BinlogListener implements CommandLineRunner {

    private final BinlogProperties binlogProperties;

    private final MessageContentHandler messageContentHandler;

    @SneakyThrows
    @Override
    public void run(String... args) {
        // 存储 TableId 和 TableName 的对应关系
        Map<Long, String> tableMap = new HashMap<>(8);

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
            if (Objects.nonNull(eventData)) {
                // 获取数据库表信息
                if (eventData instanceof TableMapEventData) {
                    TableMapEventData tableMapEventData = (TableMapEventData) eventData;
                    // 维护 TableId 和 TableName 的对应关系
                    tableMap.put(tableMapEventData.getTableId(), tableMapEventData.getDatabase() + StringConstant.POINT + tableMapEventData.getTable());
                }
                // 监听 update 操作
                if (eventData instanceof UpdateRowsEventData) {
                    // 转换为 update 监听数据
                    UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(updateRowsEventData.getTableId());
                    // 判断是否为需要监听的表
                    if (Objects.nonNull(tableName) && binlogProperties.getTable().contains(tableName)) {
                        // 打印 UPDATE 的数据表
                        log.info("BinlogListener.run(): Update operation, table -> {}", tableName);
                        // 存储监听到的修改前的数据
                        List<Serializable[]> beforeRowList = new ArrayList<>();
                        // 存储监听到的修改后的数据
                        List<Serializable[]> afterRowList = new ArrayList<>();
                        // 循环每一行修改的数据
                        for (Map.Entry<Serializable[], Serializable[]> row : updateRowsEventData.getRows()) {
                            beforeRowList.add(row.getKey());
                            afterRowList.add(row.getValue());
                        }
                        // 将数据交给消息内容处理器
                        messageContentHandler.handle(beforeRowList, afterRowList, tableName, MonitorConstant.UPDATE);
                    }
                }
                // 监听 insert 操作
                else if (eventData instanceof WriteRowsEventData) {
                    // 转换为 insert 监听数据
                    WriteRowsEventData writeRowsEventData = (WriteRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(writeRowsEventData.getTableId());
                    // 判断是否为需要监听的表
                    if (Objects.nonNull(tableName) && binlogProperties.getTable().contains(tableName)) {
                        // 打印 INSERT 的数据表
                        log.info("BinlogListener.run(): Insert operation, table -> {}", tableName);
                        // 将监听到的新增数据交给消息内容处理器
                        messageContentHandler.handle(Collections.emptyList(), writeRowsEventData.getRows(), tableName, MonitorConstant.INSERT);
                    }
                }
                // 监听 delete 操作
                else if (eventData instanceof DeleteRowsEventData) {
                    // 转换为 delete 监听数据
                    DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) eventData;
                    // 从监听数据中获取表名
                    String tableName = tableMap.get(deleteRowsEventData.getTableId());
                    // 判断是否为需要监听的表
                    if (Objects.nonNull(tableName) && binlogProperties.getTable().contains(tableName)) {
                        // 打印 DELETE 的数据表
                        log.info("BinlogListener.run(): Delete operation, table -> {}", tableName);
                        // 将监听到的删除数据交给消息内容处理器
                        messageContentHandler.handle(deleteRowsEventData.getRows(), Collections.emptyList(), tableName, MonitorConstant.DELETE);
                    }
                }
            }
        }));

        // 开启数据库连接
        binaryLogClient.connect();
        log.info("BinlogListener.run(): Listener connect success! database -> {}:{}", binlogProperties.getHost(), binlogProperties.getPort());
    }
}
