package com.cafe.monitor.debezium.handler;

import cn.hutool.json.JSONUtil;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.debezium.handler
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:23
 * @Description: Debezium 处理器入口
 */
@Component
public class DebeziumEntryHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebeziumEntryHandler.class);

    private final MessageContentHandler messageContentHandler;

    @Autowired
    public DebeziumEntryHandler(MessageContentHandler messageContentHandler) {
        this.messageContentHandler = messageContentHandler;
    }

    public void handle(List<RecordChangeEvent<SourceRecord>> recordChangeEvents, DebeziumEngine.RecordCommitter<RecordChangeEvent<SourceRecord>> recordCommitter) {
        // 监听数据库变更
        for (RecordChangeEvent<SourceRecord> recordChangeEvent : recordChangeEvents) {
            SourceRecord sourceRecord = recordChangeEvent.record();
            Struct sourceRecordChangeValue = (Struct) sourceRecord.value();
            if (Objects.isNull(sourceRecordChangeValue)) {
                continue;
            }
            // 处理变更的数据
            Map<String, Object> content = messageContentHandler.handle(sourceRecordChangeValue);
            if (CollectionUtils.isEmpty(content)) {
                continue;
            }
            // 打印日志
            LOGGER.info("Debezium 监听数据: {}", JSONUtil.toJsonStr(content));
        }

        try {
            recordCommitter.markBatchFinished();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
