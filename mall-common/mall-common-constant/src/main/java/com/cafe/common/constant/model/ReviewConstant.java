package com.cafe.common.constant.model;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.model
 * @Author: zhouboyi
 * @Date: 2025/5/10 1:27
 * @Description: 评论相关常量
 */
public class ReviewConstant {

    /**
     * 评论类型
     */
    public static class ReviewType {

        /**
         * 仅评分
         */
        public static final Integer RATING = 1;

        /**
         * 有标签
         */
        public static final Integer TAG = 2;

        /**
         * 文字评论
         */
        public static final Integer COMMENT = 3;
    }

    /**
     * 评论目标类型
     */
    public static class TargetType {

        /**
         * 商品
         */
        public static final Integer GOODS = 1;

        /**
         * 订单
         */
        public static final Integer ORDER = 2;
    }
}
