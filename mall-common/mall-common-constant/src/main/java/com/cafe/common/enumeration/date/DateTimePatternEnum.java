package com.cafe.common.enumeration.date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.date
 * @Author: zhouboyi
 * @Date: 2023/8/8 16:02
 * @Description: 日期时间格式枚举
 */
public enum DateTimePatternEnum {

    /**
     * 默认日期时间
     */
    DEFAULT_DATE_TIME("yyyy-MM-dd HH:mm:ss"),

    /**
     * 默认日期
     */
    DEFAULT_DATE("yyyy-MM-dd"),

    /**
     * 默认时间
     */
    DEFAULT_TIME("HH:mm:ss");

    /**
     * 格式
     */
    private final String pattern;

    DateTimePatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
