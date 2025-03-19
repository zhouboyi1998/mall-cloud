package com.cafe.debezium.config;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.debezium.DebeziumConstant;
import com.cafe.debezium.executor.DebeziumSyncExecutor;
import com.cafe.debezium.handler.DebeziumEntryHandler;
import com.cafe.debezium.property.DebeziumProperties;
import io.debezium.connector.mysql.MySqlConnector;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.debezium.config
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:22
 * @Description: Debezium 配置类
 */
@RequiredArgsConstructor
@Configuration
public class DebeziumConfig {

    private final DebeziumEntryHandler debeziumEntryHandler;

    private final DebeziumProperties debeziumProperties;

    /**
     * Debezium 配置 (MySQL)
     */
    @Bean
    io.debezium.config.Configuration debeziumMySQLConfig() {
        return io.debezium.config.Configuration.create()
            // MySQL 连接器
            .with(DebeziumConstant.CONNECTOR_CLASS, MySqlConnector.class.getName())
            // 监听器唯一标识
            .with(DebeziumConstant.NAME, debeziumProperties.getName())
            // 偏移量持久化处理器
            .with(DebeziumConstant.OFFSET_STORAGE, debeziumProperties.getOffset().getHandler())
            // 偏移量持久化文件
            .with(DebeziumConstant.OFFSET_STORAGE_FILE_FILENAME, debeziumProperties.getOffset().getFilename())
            // 偏移量记录周期 (单位: 毫秒)
            .with(DebeziumConstant.OFFSET_FLUSH_INTERVAL_MS, debeziumProperties.getOffset().getFlushInterval())
            // 数据库变更历史持久化处理器
            .with(DebeziumConstant.DATABASE_HISTORY, debeziumProperties.getHistory().getHandler())
            // 数据库变更历史持久化文件
            .with(DebeziumConstant.DATABASE_HISTORY_FILE_FILENAME, debeziumProperties.getHistory().getFilename())
            // 是否监听表结构的变化
            .with(DebeziumConstant.INCLUDE_SCHEMA_CHANGES, debeziumProperties.getStrategy().getSchemaChange())
            // 监听器在 MySQL 主从同步中的 server-id
            .with(DebeziumConstant.DATABASE_SERVER_ID, debeziumProperties.getStrategy().getServerId())
            // 监听器在 MySQL 主从同步中的逻辑名称
            .with(DebeziumConstant.DATABASE_SERVER_NAME, debeziumProperties.getStrategy().getServerName())
            // Host
            .with(DebeziumConstant.DATABASE_HOSTNAME, debeziumProperties.getDatasource().getHost())
            // Port
            .with(DebeziumConstant.DATABASE_PORT, debeziumProperties.getDatasource().getPort())
            // 用户名
            .with(DebeziumConstant.DATABASE_USER, debeziumProperties.getDatasource().getUsername())
            // 密码
            .with(DebeziumConstant.DATABASE_PASSWORD, debeziumProperties.getDatasource().getPassword())
            // 时区
            .with(DebeziumConstant.DATABASE_SERVER_TIMEZONE, AppConstant.DEFAULT_ZONE_ID)
            // 监听的数据库
            .with(DebeziumConstant.DATABASE_INCLUDE_LIST, debeziumProperties.getDatasource().getDatabase())
            .build();
    }

    /**
     * Debezium 同步执行器
     *
     * @param configuration Debezium 配置
     */
    @Bean
    DebeziumSyncExecutor debeziumSyncExecutor(io.debezium.config.Configuration configuration) {
        // 创建 DebeziumEngine
        DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine = DebeziumEngine
            .create(ChangeEventFormat.of(Connect.class))
            .using(configuration.asProperties())
            .notifying(debeziumEntryHandler::handle)
            .build();

        // 创建 Debezium 同步执行器
        DebeziumSyncExecutor debeziumSyncExecutor = new DebeziumSyncExecutor();
        debeziumSyncExecutor.setDebeziumEngine(debeziumEngine);
        return debeziumSyncExecutor;
    }
}
