package com.cafe.generator.plus.config.converter;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus.config.converter
 * @Author: zhouboyi
 * @Date: 2024/3/16 2:09
 * @Description: ClickHouse 数据库字段类型转换器
 */
public class ClickHouseTypeConverter implements ITypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        return TypeConverters.use(fieldType)
            .test(TypeConverters.containsAny("string", "fixedstring").then(DbColumnType.STRING))
            .test(TypeConverters.contains("int8").then(DbColumnType.BYTE))
            .test(TypeConverters.contains("uint8").then(DbColumnType.BOOLEAN))
            .test(TypeConverters.contains("int16").then(DbColumnType.SHORT))
            .test(TypeConverters.containsAny("int32", "uint16").then(DbColumnType.INTEGER))
            .test(TypeConverters.containsAny("int64", "uint32").then(DbColumnType.LONG))
            .test(TypeConverters.contains("uint64").then(DbColumnType.BIG_INTEGER))
            .test(TypeConverters.contains("float32").then(DbColumnType.FLOAT))
            .test(TypeConverters.contains("float64").then(DbColumnType.DOUBLE))
            .test(TypeConverters.contains("decimal").then(DbColumnType.BIG_DECIMAL))
            .test(TypeConverters.containsAny("date", "date32", "datetime").then(t -> toDateType(config, t)))
            .or(DbColumnType.STRING);
    }

    public static IColumnType toDateType(GlobalConfig config, String type) {
        switch (config.getDateType()) {
            case ONLY_DATE:
                return DbColumnType.DATE;
            case SQL_PACK:
                switch (type) {
                    case "date":
                    case "date32":
                        return DbColumnType.DATE_SQL;
                    default:
                        return DbColumnType.TIMESTAMP;
                }
            case TIME_PACK:
                switch (type) {
                    case "date":
                    case "date32":
                        return DbColumnType.LOCAL_DATE;
                    default:
                        return DbColumnType.LOCAL_DATE_TIME;
                }
            default:
                return DbColumnType.STRING;
        }
    }
}
