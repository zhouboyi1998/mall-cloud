package com.cafe.common.constant.request;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.request
 * @Author: zhouboyi
 * @Date: 2022/11/23 14:57
 * @Description: HTTP 请求相关常量
 */
public class RequestConstant {

    /**
     * 请求头
     */
    public static class Header {

        /**
         * 授权令牌
         */
        public static final String AUTHORIZATION = "Authorization";

        /**
         * 访问令牌
         */
        public static final String ACCESS_TOKEN = "Access-Token";

        /**
         * JWT 载荷
         */
        public static final String PAYLOAD = "Payload";

        /**
         * 是否为Feign请求
         */
        public static final String IS_FEIGN = "Is-Feign";
    }

    /**
     * 请求参数
     */
    public static class Parameter {

        /**
         * 用户id
         */
        public static final String USER_ID = "user_id";

        /**
         * 客户端id
         */
        public static final String CLIENT_ID = "client_id";

        /**
         * 用户名
         */
        public static final String USERNAME = "username";

        /**
         * 密码
         */
        public static final String PASSWORD = "password";

        /**
         * 手机号
         */
        public static final String MOBILE = "mobile";

        /**
         * 邮箱
         */
        public static final String EMAIL = "email";

        /**
         * 图片验证码唯一标识
         */
        public static final String KEY = "key";

        /**
         * 图片验证码文本
         */
        public static final String CODE = "code";
    }

    /**
     * 模型属性
     */
    public static class ModelAttribute {

        /**
         * JWT 载荷
         */
        public static final String PAYLOAD = "payload";
    }

    /**
     * 值
     */
    public static class Value {

        /**
         * 无缓存
         */
        public static final String NO_CACHE = "no-cache";
    }
}
