package com.cafe.user.constant;

import com.cafe.user.model.Role;
import com.cafe.user.model.RoleMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.constant
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:03
 * @Description: 用户模块数据库表与 JavaBean 对应关系
 */
public class UserTableBeanMap {

    public static final Map<String, Class<?>> TABLE_BEAN_MAP = new HashMap<String, Class<?>>() {{
        put(UserTable.ROLE, Role.class);
        put(UserTable.ROLE_MENU, RoleMenu.class);
    }};
}
