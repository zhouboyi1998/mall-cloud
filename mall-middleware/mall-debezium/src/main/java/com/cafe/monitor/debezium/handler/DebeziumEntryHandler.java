package com.cafe.monitor.debezium.handler;

import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

    private final KafkaContentHandler kafkaContentHandler;

    @Autowired
    public DebeziumEntryHandler(KafkaContentHandler kafkaContentHandler) {
        this.kafkaContentHandler = kafkaContentHandler;
    }

    public void handle(List<RecordChangeEvent<SourceRecord>> recordChangeEvents, DebeziumEngine.RecordCommitter<RecordChangeEvent<SourceRecord>> recordCommitter) {
        // 监听数据库变更
        for (RecordChangeEvent<SourceRecord> recordChangeEvent : recordChangeEvents) {
            SourceRecord sourceRecord = recordChangeEvent.record();
            Struct sourceRecordChangeValue = (Struct) sourceRecord.value();
            if (Objects.isNull(sourceRecordChangeValue)) {
                continue;
            }
            // 将数据交给 Kafka 消息内容处理器
            kafkaContentHandler.handle(sourceRecordChangeValue);
        }

        try {
            recordCommitter.markBatchFinished();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}