package com.cafe.generator.plus.config.builder;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.TypeConverts;
import com.cafe.generator.plus.config.converter.MariaDBTypeConverter;
import com.cafe.generator.plus.config.converter.MySQLTypeConverter;
import com.cafe.generator.plus.config.converter.PostgreSQLTypeConverter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus.config.builder
 * @Author: zhouboyi
 * @Date: 2024/1/25 15:12
 * @Description: MyBatis-Plus 代码生成器数据源配置构造器
 */
public class DataSourceConfigBuilder {

    public static DataSourceConfig build(DbType dbType) {
        switch (dbType) {
            case MYSQL:
                return new DataSourceConfig().setDbType(DbType.MYSQL).setTypeConvert(new MySQLTypeConverter());
            case MARIADB:
                return new DataSourceConfig().setDbType(DbType.MARIADB).setTypeConvert(new MariaDBTypeConverter());
            case POSTGRE_SQL:
                return new DataSourceConfig().setDbType(DbType.POSTGRE_SQL).setTypeConvert(new PostgreSQLTypeConverter());
            default:
                return new DataSourceConfig().setDbType(dbType).setTypeConvert(TypeConverts.getTypeConvert(dbType));
        }
    }
}
