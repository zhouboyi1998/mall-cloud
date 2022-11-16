package com.cafe.common.enumeration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration
 * @Author: zhouboyi
 * @Date: 2022/5/19 9:21
 * @Description: HTTP 请求头值枚举
 */
public enum HttpHeaderValueEnum {

    /**
     * 无缓存
     */
    NO_CACHE("no-cache");

    /**
     * 请求头值
     */
    private final String value;

    HttpHeaderValueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
