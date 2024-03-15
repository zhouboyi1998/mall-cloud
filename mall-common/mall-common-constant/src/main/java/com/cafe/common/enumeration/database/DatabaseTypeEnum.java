package com.cafe.common.enumeration.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.database
 * @Author: zhouboyi
 * @Date: 2024/3/16 3:59
 * @Description: 数据库类型枚举
 */
@Getter
@AllArgsConstructor
public enum DatabaseTypeEnum {

    /**
     * ClickHouse
     */
    CLICK_HOUSE("clickhouse", "ClickHouse 数据库"),

    /**
     * UNKNOWN DB
     */
    OTHER("other", "其它数据库");

    /**
     * 数据库名称
     */
    private final String db;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 获取数据库类型
     *
     * @param dbType 数据库类型字符串
     */
    public static DatabaseTypeEnum getDbType(String dbType) {
        for (DatabaseTypeEnum type : DatabaseTypeEnum.values()) {
            if (type.db.equalsIgnoreCase(dbType)) {
                return type;
            }
        }
        return OTHER;
    }
}
