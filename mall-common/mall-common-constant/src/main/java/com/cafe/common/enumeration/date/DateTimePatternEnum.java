package com.cafe.common.enumeration.date;

import com.cafe.common.constant.date.DateTimeConstant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.date
 * @Author: zhouboyi
 * @Date: 2023/8/8 16:02
 * @Description: 日期时间格式枚举
 */
public enum DateTimePatternEnum {

    /**
     * 默认日期时间格式
     */
    DEFAULT_DATE_TIME(DateTimeConstant.DEFAULT_DATE_TIME),

    /**
     * 默认日期格式
     */
    DEFAULT_DATE(DateTimeConstant.DEFAULT_DATE),

    /**
     * 默认时间格式
     */
    DEFAULT_TIME(DateTimeConstant.DEFAULT_TIME);

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
