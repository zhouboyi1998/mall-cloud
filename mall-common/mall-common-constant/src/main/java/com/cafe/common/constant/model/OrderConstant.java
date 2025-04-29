package com.cafe.common.constant.model;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.model
 * @Author: zhouboyi
 * @Date: 2023/5/5 17:45
 * @Description: 订单相关常量
 */
public class OrderConstant {

    /**
     * 状态
     */
    public static class Status {

        /**
         * 下单
         */
        public static final Integer CREATE = 0;

        /**
         * 取消
         */
        public static final Integer CANCEL = 6;
    }

    /**
     * 评价状态
     */
    public static class Review {

        /**
         * 未评价
         */
        public static final Integer NOT_REVIEW = 0;

        /**
         * 已评价
         */
        public static final Integer REVIEWED = 1;
    }
}
