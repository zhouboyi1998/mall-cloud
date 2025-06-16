package com.cafe.common.constant.opensearch;

import com.cafe.common.constant.app.FieldConstant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.opensearch
 * @Author: zhouboyi
 * @Date: 2025/6/16 21:20
 * @Description: OpenSearch 相关常量
 */
public class OpenSearchConstant {

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
}
