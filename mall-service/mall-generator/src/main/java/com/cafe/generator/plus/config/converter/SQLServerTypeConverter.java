package com.cafe.generator.plus.config.converter;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.cafe.common.enumeration.database.ColumnTypeEnum;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus.config.converter
 * @Author: zhouboyi
 * @Date: 2024/1/30 14:56
 * @Description: SQL Server 数据库字段类型转换器
 */
public class SQLServerTypeConverter extends SqlServerTypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        switch (ColumnTypeEnum.getColumnType(fieldType.toUpperCase())) {
            case DECIMAL:
            case NUMERIC:
                return DbColumnType.BIG_DECIMAL;
            case TIMESTAMP:
            case DATETIME:
                return DbColumnType.LOCAL_DATE_TIME;
            case DATE:
                return DbColumnType.LOCAL_DATE;
            case TIME:
                return DbColumnType.LOCAL_TIME;
            default:
                return super.processTypeConvert(config, fieldType);
        }
    }
}
