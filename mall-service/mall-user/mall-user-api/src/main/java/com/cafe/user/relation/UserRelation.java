package com.cafe.user.relation;

import com.cafe.common.constant.mysql.MySQLConstant;
import com.cafe.user.model.Role;
import com.cafe.user.model.RoleMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.relation
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:03
 * @Description: 用户模块数据库表和 Java 模型的对应关系
 */
public class UserRelation {

    /**
     * Table --> Java Model
     */
    public static final Map<String, Class<?>> TABLE_MODEL_MAP = new HashMap<>();

    static {
        TABLE_MODEL_MAP.put(MySQLConstant.Table.ROLE, Role.class);
        TABLE_MODEL_MAP.put(MySQLConstant.Table.ROLE_MENU, RoleMenu.class);
    }
}
