package com.cafe.monitor.binlog.database.constant;

import com.cafe.admin.model.RoleMenuRelation;
import com.cafe.common.constant.DatabaseTable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog.database.constant
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:03
 * @Description: 数据库表与 JavaBean 对应关系
 */
public class DatabaseTableAndBean {

    public static final Map<String, Class<?>> TABLE_BEAN_MAP = new HashMap<String, Class<?>>() {{
        put(DatabaseTable.ROLE_MENU_RELATION, RoleMenuRelation.class);
    }};
}
