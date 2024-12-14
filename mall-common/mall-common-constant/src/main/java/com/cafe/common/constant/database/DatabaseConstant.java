package com.cafe.common.constant.database;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.database
 * @Author: zhouboyi
 * @Date: 2023/4/27 10:56
 * @Description: 数据库相关常量
 */
public class DatabaseConstant {

    /**
     * 数据库
     */
    public static class Database {

        /**
         * 用户数据库
         */
        public static final String USER = "mall_user";

        /**
         * 商品数据库
         */
        public static final String GOODS = "mall_goods";

        /**
         * 订单数据库
         */
        public static final String ORDER = "mall_order";
    }

    /**
     * 数据表
     */
    public static class Table {

        /**
         * 角色表
         */
        public static final String ROLE = "mall_role";

        /**
         * 角色-资源关联关系表
         */
        public static final String ROLE_RESOURCE = "mall_role_resource";

        /**
         * 品牌表
         */
        public static final String BRAND = "mall_brand";

        /**
         * 分类表
         */
        public static final String CATEGORY = "mall_category";

        /**
         * 订单表
         */
        public static final String ORDER = "mall_order";

        /**
         * 订单详情表
         */
        public static final String ORDER_DETAIL = "mall_order_detail";
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

        /**
         * 逻辑删除
         */
        public static final String IS_DELETED = "is_deleted";
    }

    /**
     * 排序规则
     */
    public static class Rule {

        /**
         * 升序
         */
        public static final String ASC = "asc";

        /**
         * 降序
         */
        public static final String DESC = "desc";
    }
}
