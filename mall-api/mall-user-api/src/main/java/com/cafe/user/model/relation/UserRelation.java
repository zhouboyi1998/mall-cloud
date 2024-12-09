package com.cafe.user.model.relation;

import com.cafe.common.constant.mysql.MySQLConstant;
import com.cafe.user.model.entity.Role;
import com.cafe.user.model.entity.RoleMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.model.relation
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
        TABLE_MODEL_MAP.put(MySQLConstant.DatabaseTable.ROLE, Role.class);
        TABLE_MODEL_MAP.put(MySQLConstant.DatabaseTable.ROLE_MENU, RoleMenu.class);
    }
}
