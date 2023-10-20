package com.cafe.generator.plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.cafe.common.enumeration.database.ColumnTypeEnum;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus
 * @Author: zhouboyi
 * @Date: 2023/5/9 19:18
 * @Description: MyBatis-Plus 代码生成器动态数据源配置
 */
public class DynamicDataSourceConfig {

    public static DataSourceConfig database(String dbType) {
        switch (DbType.getDbType(dbType)) {
            case MYSQL:
                return mysql();
            case POSTGRE_SQL:
                return postgresql();
            default:
                return new DataSourceConfig();
        }
    }

    public static DataSourceConfig mysql() {
        return new DataSourceConfig()
            .setDbType(DbType.MYSQL)
            // 指定 MySQL 数据类型和 Java 数据类型的映射
            .setTypeConvert(new MySqlTypeConvert() {
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
            });
    }

    public static DataSourceConfig postgresql() {
        return new DataSourceConfig()
            .setDbType(DbType.POSTGRE_SQL)
            // 指定 PostgreSQL 数据类型和 Java 数据类型的映射
            .setTypeConvert(new PostgreSqlTypeConvert() {
                @Override
                public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                    switch (ColumnTypeEnum.getColumnType(fieldType.toUpperCase())) {
                        case DECIMAL:
                        case NUMERIC:
                            return DbColumnType.BIG_DECIMAL;
                        case TIMESTAMP:
                            return DbColumnType.LOCAL_DATE_TIME;
                        case DATE:
                            return DbColumnType.LOCAL_DATE;
                        case TIME:
                            return DbColumnType.LOCAL_TIME;
                        default:
                            return super.processTypeConvert(config, fieldType);
                    }
                }
            });
    }
}
