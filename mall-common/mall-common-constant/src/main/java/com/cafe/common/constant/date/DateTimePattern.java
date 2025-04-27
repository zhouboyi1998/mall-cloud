package com.cafe.common.constant.date;

import java.util.regex.Pattern;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.date
 * @Author: zhouboyi
 * @Date: 2025/4/18 17:41
 * @Description: 日期时间相关正则表达式
 */
public class DateTimePattern {

    /**
     * 默认日期时间格式 (yyyy-MM-dd HH:mm:ss)
     */
    public static final Pattern DEFAULT_DATE_TIME = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1\\d|2[0-3]):([0-5]\\d):([0-5]\\d)");

    /**
     * 默认日期格式 (yyyy-MM-dd)
     */
    public static final Pattern DEFAULT_DATE = Pattern.compile("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])");

    /**
     * 默认时间格式 (HH:mm:ss)
     */
    public static final Pattern DEFAULT_TIME = Pattern.compile("(0[0-9]|1\\d|2[0-3]):([0-5]\\d):([0-5]\\d)");
}
