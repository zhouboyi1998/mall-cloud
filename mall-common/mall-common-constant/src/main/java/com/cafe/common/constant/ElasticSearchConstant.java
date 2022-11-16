package com.cafe.common.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/7/28 10:03
 * @Description: ElasticSearch 相关常量
 */
public class ElasticSearchConstant {

    /**
     * 商品索引
     */
    public static final String GOODS_INDEX = "goods";

    /**
     * 商品搜索关键词匹配字段
     */
    public static final String[] GOODS_SEARCH_FIELD = {
        FieldConstant.BRAND_NAME,
        FieldConstant.CATEGORY_NAME,
        FieldConstant.SPU_NAME,
        FieldConstant.SKU_NAME
    };

    /**
     * 商品默认排序字段
     */
    public static final String GOODS_DEFAULT_SORT = "_id";

    /**
     * 商品默认排序规则
     */
    public static final String GOODS_DEFAULT_RULE = "asc";
}
