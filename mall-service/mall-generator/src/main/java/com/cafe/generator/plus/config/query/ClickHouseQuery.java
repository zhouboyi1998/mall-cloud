package com.cafe.generator.plus.config.query;

import com.baomidou.mybatisplus.generator.config.querys.AbstractDbQuery;

import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus.config.query
 * @Author: zhouboyi
 * @Date: 2024/3/16 4:56
 * @Description: ClickHouse 表数据查询
 */
public class ClickHouseQuery extends AbstractDbQuery {

    private final Properties properties;

    public ClickHouseQuery(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String tablesSql() {
        return "SELECT * FROM system.tables WHERE database = '" + properties.getProperty("database") + "'";
    }

    @Override
    public String tableFieldsSql() {
        return "SELECT * FROM system.columns WHERE database = '" + properties.getProperty("database") + "' AND table = '%s'";
    }

    @Override
    public String tableName() {
        return "name";
    }

    @Override
    public String tableComment() {
        return "comment";
    }

    @Override
    public String fieldName() {
        return "name";
    }

    @Override
    public String fieldType() {
        return "type";
    }

    @Override
    public String fieldComment() {
        return "comment";
    }

    @Override
    public String fieldKey() {
        return "is_in_primary_key";
    }
}
