package com.cafe.debezium.handler;

import com.cafe.common.constant.kafka.KafkaConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.enumeration.debezium.SchemaFieldEnum;
import com.cafe.common.kafka.producer.KafkaProducer;
import org.apache.kafka.connect.data.Struct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.debezium.handler
 * @Author: zhouboyi
 * @Date: 2023/4/25 15:19
 * @Description: Kafka 消息内容处理器
 */
@Component
public class KafkaContentHandler {

    private final MessageContentHandler messageContentHandler;

    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaContentHandler(
        MessageContentHandler messageContentHandler,
        KafkaProducer kafkaProducer
    ) {
        this.messageContentHandler = messageContentHandler;
        this.kafkaProducer = kafkaProducer;
    }

    public void handle(Struct sourceRecordChangeValue) {
        // 组装消息
        Map<String, Object> content = messageContentHandler.handle(sourceRecordChangeValue);
        if (CollectionUtils.isEmpty(content)) {
            return;
        }
        // 发送消息到 Kafka
        String tableName = content.get(SchemaFieldEnum.DATABASE.getSchemaField()) +
            StringConstant.POINT +
            content.get(SchemaFieldEnum.TABLE.getSchemaField());
        kafkaProducer.send(KafkaConstant.Topic.MAP.get(KafkaConstant.Producer.DEBEZIUM, tableName), content);
    }
}
