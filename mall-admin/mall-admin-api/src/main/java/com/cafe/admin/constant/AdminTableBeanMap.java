package com.cafe.admin.constant;

import com.cafe.admin.model.RoleMenuRelation;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.constant
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:03
 * @Description: 管理员模块数据库表与 JavaBean 对应关系
 */
public class AdminTableBeanMap {

    public static final Map<String, Class<?>> TABLE_BEAN_MAP = new HashMap<String, Class<?>>() {{
        put(AdminTable.ROLE_MENU_RELATION, RoleMenuRelation.class);
    }};
}
