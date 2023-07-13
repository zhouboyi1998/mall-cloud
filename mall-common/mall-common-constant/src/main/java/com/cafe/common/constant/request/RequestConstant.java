package com.cafe.common.constant.request;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.request
 * @Author: zhouboyi
 * @Date: 2022/11/23 14:57
 * @Description: HTTP 请求相关常量
 */
public class RequestConstant {

    // -------------------- REQUEST HEADER KEY --------------------

    /**
     * 访问令牌
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 访问令牌前缀
     */
    public static final String BEARER_PREFIX = "Bearer ";

    /**
     * 用户详细信息
     */
    public static final String USER_DETAILS = "User-Details";

    /**
     * 是否为Feign请求
     */
    public static final String IS_FEIGN = "Is-Feign";

    // -------------------- REQUEST HEADER VALUE --------------------

    /**
     * 无缓存
     */
    public static final String NO_CACHE = "no-cache";

    // -------------------- REQUEST PARAMETER KEY --------------------

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
     * 图片验证码唯一标识
     */
    public static final String KEY = "key";

    /**
     * 图片验证码文本
     */
    public static final String CODE = "code";
}
