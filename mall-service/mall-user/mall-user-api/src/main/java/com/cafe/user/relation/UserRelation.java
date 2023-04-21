package com.cafe.user.relation;

import com.cafe.common.constant.mysql.TableConstant;
import com.cafe.user.model.Role;
import com.cafe.user.model.RoleMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.relation
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:03
 * @Description: 用户模块关系常量
 */
public class UserRelation {

    /**
     * Database Table <--> Java Model
     */
    public static final Map<String, Class<?>> TABLE_MODEL_MAP = new HashMap<>();

    static {
        TABLE_MODEL_MAP.put(TableConstant.ROLE, Role.class);
        TABLE_MODEL_MAP.put(TableConstant.ROLE_MENU, RoleMenu.class);
    }
}
