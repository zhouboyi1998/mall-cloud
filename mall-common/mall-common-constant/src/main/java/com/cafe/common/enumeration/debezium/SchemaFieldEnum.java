package com.cafe.common.enumeration.debezium;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.debezium
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:37
 * @Description: Debezium 数据库变更信息枚举
 */
@Getter
@AllArgsConstructor
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

    /**
     * 变更信息字段
     */
    private final String schemaField;

    public static Boolean contains(String schemaField) {
        return Stream.of(values())
            .map(SchemaFieldEnum::getSchemaField)
            .collect(Collectors.toSet())
            .contains(schemaField);
    }
}
