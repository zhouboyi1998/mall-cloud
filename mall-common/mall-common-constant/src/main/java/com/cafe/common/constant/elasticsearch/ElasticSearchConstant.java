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
     * 商品索引
     */
    public static class Goods {

        /**
         * 索引名称
         */
        public static final String INDEX = "goods-index";

        /**
         * 默认排序字段
         */
        public static final String DEFAULT_SORT_FIELD = FieldConstant.SALE;

        /**
         * 商品搜索关键词匹配字段
         */
        public static final String[] SEARCH_FIELD = {
            FieldConstant.SKU_NAME,
            FieldConstant.SPU_NAME,
            FieldConstant.BRAND_NAME,
            FieldConstant.CATEGORY_NAME,
            FieldConstant.SPECIFICATION,
            FieldConstant.SHOP_NAME
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
