package com.cafe.common.constant.mysql;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.mysql
 * @Author: zhouboyi
 * @Date: 2023/4/27 10:56
 * @Description: MySQL 相关常量
 */
public class MySQLConstant {

    /**
     * 数据库表
     */
    public static class Table {

        /**
         * 用户数据库.角色表
         */
        public static final String ROLE = "mall_user.mall_role";

        /**
         * 用户数据库.角色-菜单关联关系表
         */
        public static final String ROLE_MENU = "mall_user.mall_role_menu";

        /**
         * 商品数据库.品牌表
         */
        public static final String BRAND = "mall_goods.mall_brand";

        /**
         * 商品数据库.分类表
         */
        public static final String CATEGORY = "mall_goods.mall_category";

        /**
         * 订单数据库.订单表
         */
        public static final String ORDER = "mall_order.mall_order";

        /**
         * 订单数据库.订单详情表
         */
        public static final String ORDER_DETAIL = "mall_order.mall_order_detail";
    }

    /**
     * 数据库字段
     */
    public static class Column {

        /**
         * 创建时间
         */
        public static final String CREATE_TIME = "create_time";

        /**
         * 更新时间
         */
        public static final String UPDATE_TIME = "update_time";
    }
}
