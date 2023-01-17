package com.cafe.monitor.binlog.bean;

import com.cafe.common.constant.mysql.TableConstant;
import com.cafe.user.model.Role;
import com.cafe.user.model.RoleMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog.bean
 * @Author: zhouboyi
 * @Date: 2022/5/19 17:03
 * @Description: 数据库表与 Java 模型对应关系
 */
public class TableBeanMap {

    /**
     * 通过数据库表名称获取对应的 Java 模型
     */
    public static final Map<String, Class<?>> TABLE_BEAN_MAP = new HashMap<>();

    static {
        TABLE_BEAN_MAP.put(TableConstant.ROLE, Role.class);
        TABLE_BEAN_MAP.put(TableConstant.ROLE_MENU, RoleMenu.class);
    }
}
