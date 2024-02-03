package com.cafe.generator.plus.config.converter;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.cafe.common.constant.oracle.OracleConstant;
import com.cafe.common.enumeration.database.ColumnTypeEnum;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus.config.converter
 * @Author: zhouboyi
 * @Date: 2024/2/4 0:53
 * @Description: Oracle 数据库字段类型转换器
 */
public class OracleTypeConverter extends OracleTypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        switch (ColumnTypeEnum.getColumnType(fieldType.toUpperCase())) {
            case DATE:
            case TIMESTAMP:
                return DbColumnType.LOCAL_DATE_TIME;
            default:
                if (fieldType.matches(OracleConstant.Pattern.NUMBER_PATTERN)) {
                    return toNumberType(fieldType);
                } else {
                    return super.processTypeConvert(config, fieldType);
                }
        }
    }

    private static IColumnType toNumberType(String typeName) {
        if (typeName.matches(OracleConstant.Pattern.NUMBER_BIG_DECIMAL_PATTERN)) {
            return DbColumnType.BIG_DECIMAL;
        } else if (typeName.matches(OracleConstant.Pattern.NUMBER_BOOLEAN_PATTERN)) {
            return DbColumnType.BOOLEAN;
        } else if (typeName.matches(OracleConstant.Pattern.NUMBER_INTEGER_PATTERN)) {
            return DbColumnType.INTEGER;
        } else if (typeName.matches(OracleConstant.Pattern.NUMBER_LONG_PATTERN)) {
            return DbColumnType.LONG;
        }
        return DbColumnType.BIG_DECIMAL;
    }
}
