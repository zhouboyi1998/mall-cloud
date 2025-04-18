package com.cafe.common.util.date;

import com.cafe.common.constant.date.DateTimeConstant;

import java.text.SimpleDateFormat;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.date
 * @Author: zhouboyi
 * @Date: 2025/4/18 18:11
 * @Description: Date 类型格式化器持有者
 */
public class DateFormatterHolder {

    /**
     * 默认日期时间格式化器 (yyyy-MM-dd HH:mm:ss)
     */
    public static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_TIME_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateTimeConstant.DEFAULT_DATE_TIME));

    /**
     * 默认日期格式化器 (yyyy-MM-dd)
     */
    public static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateTimeConstant.DEFAULT_DATE));

    /**
     * 默认时间格式化器 (HH:mm:ss)
     */
    public static final ThreadLocal<SimpleDateFormat> DEFAULT_TIME_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateTimeConstant.DEFAULT_TIME));
}
