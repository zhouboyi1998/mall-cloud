package com.cafe.debezium.handler;

import com.cafe.common.constant.monitor.MonitorConstant;
import com.cafe.common.enumeration.debezium.SchemaFieldEnum;
import io.debezium.data.Envelope;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.debezium.data.Envelope.FieldName.AFTER;
import static io.debezium.data.Envelope.FieldName.BEFORE;
import static io.debezium.data.Envelope.FieldName.OPERATION;
import static java.util.stream.Collectors.toMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.debezium.handler
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:24
 * @Description: 消息内容处理器
 */
@Component
public class MessageContentHandler {

    public Map<String, Object> handle(Struct sourceRecordChangeValue) {
        // 处理变更数据库、数据表、时间信息
        Map<String, Object> result = convertSchemaDataToMap(sourceRecordChangeValue);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }

        // 跳过 READ、TRUNCATE 操作类型, 只处理 CREATE、UPDATE、DELETE 操作类型
        Envelope.Operation operation = Envelope.Operation.forCode((String) sourceRecordChangeValue.get(OPERATION));
        if (Objects.equals(Envelope.Operation.READ, operation) || Objects.equals(Envelope.Operation.TRUNCATE, operation)) {
            return null;
        }

        // 处理新增的行数据
        if (operation == Envelope.Operation.CREATE) {
            result.put(MonitorConstant.AFTER_DATA, convertRowDataToMap(sourceRecordChangeValue, AFTER));
        }

        // 处理更新的行数据
        if (operation == Envelope.Operation.UPDATE) {
            result.put(MonitorConstant.BEFORE_DATA, convertRowDataToMap(sourceRecordChangeValue, BEFORE));
            result.put(MonitorConstant.AFTER_DATA, convertRowDataToMap(sourceRecordChangeValue, AFTER));
        }

        // 处理删除的行数据
        if (operation == Envelope.Operation.DELETE) {
            result.put(MonitorConstant.BEFORE_DATA, convertRowDataToMap(sourceRecordChangeValue, BEFORE));
        }

        result.put(MonitorConstant.OPERATION, operation.code());
        return result;
    }

    private Map<String, Object> convertSchemaDataToMap(Struct sourceRecordChangeValue) {
        // 变更的数据库、数据表、时间信息转换为 Map
        Struct struct = (Struct) sourceRecordChangeValue.get(MonitorConstant.SOURCE);
        List<Field> fields = struct.schema().fields();
        return fields.stream()
            .map(Field::name)
            .filter(SchemaFieldEnum::contains)
            .map(schemaField -> Pair.of(schemaField, struct.get(schemaField)))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    public Map<String, Object> convertRowDataToMap(Struct sourceRecordChangeValue, String record) {
        // 将变更的行数据转换为 Map
        Struct struct = (Struct) sourceRecordChangeValue.get(record);
        List<Field> fields = struct.schema().fields();
        return fields.stream()
            .map(Field::name)
            .filter(fieldName -> struct.get(fieldName) != null)
            .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
            .collect(toMap(Pair::getKey, Pair::getValue));
    }
}
