package com.cafe.common.enumeration.http;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.http
 * @Author: zhouboyi
 * @Date: 2024/4/23 21:08
 * @Description: 自定义请求方式枚举
 */
public enum HttpMethodEnum {

    /**
     * GET 请求方式
     */
    GET("GET"),

    /**
     * POST 请求方式
     */
    POST("POST"),

    /**
     * PUT 请求方式
     */
    PUT("PUT"),

    /**
     * PATCH 请求方式
     */
    PATCH("PATCH"),

    /**
     * DELETE 请求方式
     */
    DELETE("DELETE"),

    /**
     * HEAD 请求方式
     */
    HEAD("HEAD"),

    /**
     * OPTIONS 请求方式
     */
    OPTIONS("OPTIONS"),

    /**
     * 未知的请求方式
     */
    UNKNOWN("UNKNOWN");

    private final String name;

    HttpMethodEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static HttpMethodEnum getHttpMethodByName(String name) {
        HttpMethodEnum[] httpMethodEnums = values();
        for (HttpMethodEnum httpMethodEnum : httpMethodEnums) {
            if (Objects.equals(httpMethodEnum.getName(), name)) {
                return httpMethodEnum;
            }
        }
        return UNKNOWN;
    }
}
