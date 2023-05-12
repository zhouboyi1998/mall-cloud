package com.cafe.common.constant.mysql;

import com.cafe.common.constant.database.DatabaseConstant;
import com.cafe.common.constant.pool.StringConstant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.mysql
 * @Author: zhouboyi
 * @Date: 2023/5/10 11:16
 * @Description: MySQL 相关常量
 */
public class MySQLConstant {

    public static class DatabaseTable {

        public static final String ROLE = DatabaseConstant.Database.USER + StringConstant.POINT + DatabaseConstant.Table.ROLE;

        public static final String ROLE_MENU = DatabaseConstant.Database.USER + StringConstant.POINT + DatabaseConstant.Table.ROLE_MENU;

        public static final String BRAND = DatabaseConstant.Database.GOODS + StringConstant.POINT + DatabaseConstant.Table.BRAND;

        public static final String CATEGORY = DatabaseConstant.Database.GOODS + StringConstant.POINT + DatabaseConstant.Table.CATEGORY;

        public static final String ORDER = DatabaseConstant.Database.ORDER + StringConstant.POINT + DatabaseConstant.Table.ORDER;

        public static final String ORDER_DETAIL = DatabaseConstant.Database.ORDER + StringConstant.POINT + DatabaseConstant.Table.ORDER_DETAIL;
    }
}
