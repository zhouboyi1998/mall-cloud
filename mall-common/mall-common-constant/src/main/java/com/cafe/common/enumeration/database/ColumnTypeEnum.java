package com.cafe.common.enumeration.database;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus
 * @Author: zhouboyi
 * @Date: 2022/11/23 14:36
 * @Description: 数据库字段类型枚举
 */
public enum ColumnTypeEnum {

    /**
     * 整型
     */
    INT,

    /**
     * 长整型
     */
    BIGINT,

    /**
     * 单字节整型
     */
    TINYINT,

    /**
     * 精准数据类型
     */
    DECIMAL,

    /**
     * 精准数据类型
     */
    NUMERIC,

    /**
     * 动态字符型
     */
    VARCHAR,

    /**
     * 时间戳类型
     */
    TIMESTAMP,

    /**
     * 日期类型
     */
    DATE,

    /**
     * 时间类型
     */
    TIME,

    /**
     * 日期时间类型
     */
    DATETIME,

    /**
     * 其它类型
     */
    NOT_MATCH;

    public static ColumnTypeEnum getColumnType(String fieldType) {
        ColumnTypeEnum[] columnTypes = ColumnTypeEnum.values();
        for (ColumnTypeEnum columnType : columnTypes) {
            if (Objects.equals(columnType.toString(), fieldType)) {
                return columnType;
            }
        }
        return NOT_MATCH;
    }
}
