package com.cafe.common.constant.elasticsearch;

import com.cafe.common.constant.app.FieldConstant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.elasticsearch
 * @Author: zhouboyi
 * @Date: 2022/7/28 10:03
 * @Description: ElasticSearch 相关常量
 */
public class ElasticSearchConstant {

    /**
     * 默认排序字段
     */
    public static final String DEFAULT_SORT = "_id";

    /**
     * 默认排序规则
     */
    public static final String DEFAULT_RULE = "asc";

    /**
     * 商品索引
     */
    public static class Goods {

        /**
         * 索引名称
         */
        public static final String INDEX = "goods-index";

        /**
         * 商品搜索关键词匹配字段
         */
        public static final String[] SEARCH_FIELD = {
            FieldConstant.BRAND_NAME,
            FieldConstant.CATEGORY_NAME,
            FieldConstant.SPU_NAME,
            FieldConstant.SKU_NAME
        };
    }

    /**
     * 订单VO索引
     */
    public static class Order {

        /**
         * 索引名称
         */
        public static final String INDEX = "order-index";
    }
}
