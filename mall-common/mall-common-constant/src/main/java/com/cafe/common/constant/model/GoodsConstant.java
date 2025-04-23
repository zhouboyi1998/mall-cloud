package com.cafe.common.constant.model;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.model
 * @Author: zhouboyi
 * @Date: 2023/5/5 17:45
 * @Description: 商品相关常量
 */
public class GoodsConstant {

    /**
     * 状态
     */
    public static class Status {

        /**
         * 上架
         */
        public static final Integer ON_SHELVE = 1;

        /**
         * 下架
         */
        public static final Integer OFF_SHELVE = 0;
    }

    /**
     * 查询类型
     */
    public static class QueryType {

        /**
         * 所有字段
         */
        public static final String FULL = "FULL";

        /**
         * 全文索引字段
         */
        public static final String INDEX = "INDEX";

        /**
         * 概要字段
         */
        public static final String SUMMARY = "SUMMARY";
    }
}
