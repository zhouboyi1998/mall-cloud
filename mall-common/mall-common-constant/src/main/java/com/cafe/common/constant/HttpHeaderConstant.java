package com.cafe.common.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/11/23 14:57
 * @Description: HTTP 请求头相关常量
 */
public class HttpHeaderConstant {

    /**
     * 访问令牌请求头
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 访问令牌前缀
     */
    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    /**
     * 用户详细信息请求头
     */
    public static final String USER_DETAILS_HEADER = "User-Details";

    /**
     * Cache-Control 请求头值: 无缓存
     */
    public static final String NO_CACHE = "no-cache";
}
