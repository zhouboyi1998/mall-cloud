package com.cafe.search.elasticsearch.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.constant
 * @Author: zhouboyi
 * @Date: 2022/7/28 10:03
 * @Description:
 */
public class ElasticSearchConstant {

    /**
     * 商品索引
     */
    public static final String GOODS_INDEX = "goods";

    /**
     * 商品搜索匹配字段
     */
    public static final String[] GOODS_SEARCH_FIELD = {"name", "skuName", "spuName", "brandName", "categoryName"};

    /**
     * 商品默认排序字段
     */
    public static final String GOODS_DEFAULT_SORT = "_id";

    /**
     * 商品默认排序规则
     */
    public static final String GOODS_DEFAULT_RULE = "asc";
}
