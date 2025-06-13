package com.cafe.common.constant.meilisearch;

import com.cafe.common.constant.app.FieldConstant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.meilisearch
 * @Author: zhouboyi
 * @Date: 2025/6/6 23:59
 * @Description: MeiliSearch 相关常量
 */
public class MeiliSearchConstant {

    /**
     * 商品索引
     */
    public static class Goods {

        /**
         * 索引名称
         */
        public static final String INDEX = "goods-index";

        /**
         * 主键字段
         */
        public static final String PRIMARY_KEY = FieldConstant.ID;

        /**
         * 全文搜索字段
         */
        public static final String[] SEARCH_FIELD = {
            FieldConstant.SKU_NAME,
            FieldConstant.SPU_NAME,
            FieldConstant.BRAND_NAME,
            FieldConstant.CATEGORY_NAME,
            FieldConstant.SPECIFICATION,
            FieldConstant.SHOP_NAME
        };

        /**
         * 过滤字段
         */
        public static final String[] FILTER_FIELD = {
            FieldConstant.ID,
            FieldConstant.SPU_ID,
            FieldConstant.BRAND_ID,
            FieldConstant.CATEGORY_ID,
            FieldConstant.SHOP_ID
        };

        /**
         * 默认排序字段
         */
        public static final String DEFAULT_SORT_FIELD = FieldConstant.SALE;

        /**
         * 可排序字段
         */
        public static final String[] SORT_FIELD = {
            FieldConstant.ORIGINAL_PRICE,
            FieldConstant.DISCOUNT_PRICE,
            FieldConstant.SECKILL_PRICE,
            FieldConstant.SALE,
            FieldConstant.TOTAL_REVIEW,
            FieldConstant.GOOD_REVIEW,
            FieldConstant.MEDIUM_REVIEW,
            FieldConstant.BAD_REVIEW
        };
    }
}
