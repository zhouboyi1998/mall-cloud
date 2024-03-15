package com.cafe.generator.plus.config.builder;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.TypeConverts;
import com.cafe.common.enumeration.database.DatabaseTypeEnum;
import com.cafe.generator.plus.config.converter.ClickHouseTypeConverter;
import com.cafe.generator.plus.config.converter.MariaDBTypeConverter;
import com.cafe.generator.plus.config.converter.MySQLTypeConverter;
import com.cafe.generator.plus.config.converter.OracleTypeConverter;
import com.cafe.generator.plus.config.converter.PostgreSQLTypeConverter;
import com.cafe.generator.plus.config.converter.SQLServerTypeConverter;
import com.cafe.generator.plus.config.query.ClickHouseQuery;

import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus.config.builder
 * @Author: zhouboyi
 * @Date: 2024/1/25 15:12
 * @Description: MyBatis-Plus 代码生成器数据源配置构造器
 */
public class DataSourceConfigBuilder {

    public static DataSourceConfig build(String databaseType, Properties properties) {
        DbType dbType = DbType.getDbType(databaseType);
        switch (dbType) {
            case MYSQL:
                return new DataSourceConfig().setDbType(dbType).setTypeConvert(new MySQLTypeConverter());
            case MARIADB:
                return new DataSourceConfig().setDbType(dbType).setTypeConvert(new MariaDBTypeConverter());
            case POSTGRE_SQL:
                return new DataSourceConfig().setDbType(dbType).setTypeConvert(new PostgreSQLTypeConverter());
            case SQL_SERVER:
                return new DataSourceConfig().setDbType(dbType).setTypeConvert(new SQLServerTypeConverter());
            case ORACLE_12C:
                return new DataSourceConfig().setDbType(dbType).setTypeConvert(new OracleTypeConverter());
            default:
                switch (DatabaseTypeEnum.getDbType(databaseType)) {
                    case CLICK_HOUSE:
                        return new DataSourceConfig().setDbQuery(new ClickHouseQuery(properties)).setTypeConvert(new ClickHouseTypeConverter());
                    default:
                        return new DataSourceConfig().setDbType(dbType).setTypeConvert(TypeConverts.getTypeConvert(dbType));
                }
        }
    }
}
