package com.cafe.common.enumeration.debezium;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.debezium
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:37
 * @Description: Debezium 数据库变更信息枚举
 */
public enum SchemaFieldEnum {

    /**
     * 数据库
     */
    DATABASE("db"),

    /**
     * 数据表
     */
    TABLE("table"),

    /**
     * 操作时间
     */
    OPERATION_TIME("ts_ms");

    private final String schemaField;

    SchemaFieldEnum(String schemaField) {
        this.schemaField = schemaField;
    }

    public String getSchemaField() {
        return schemaField;
    }

    public static Boolean contains(String schemaField) {
        return Stream.of(values())
            .map(SchemaFieldEnum::getSchemaField)
            .collect(Collectors.toSet())
            .contains(schemaField);
    }
}
