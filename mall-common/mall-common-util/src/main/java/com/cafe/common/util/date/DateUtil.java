package com.cafe.common.util.date;

import com.cafe.common.constant.date.DateTimeConstant;
import com.cafe.common.constant.date.DateTimePattern;
import com.cafe.common.constant.pool.StringConstant;
import lombok.SneakyThrows;

import java.util.Calendar;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.date
 * @Author: zhouboyi
 * @Date: 2025/4/18 17:42
 * @Description: Date 工具类
 */
public class DateUtil {

    /**
     * 解析日期时间字符串成 Date 类型
     *
     * @param text 日期时间字符串
     * @return Date 对象
     */
    @SneakyThrows
    public static Date parseDate(String text) {
        switch (pattern(text)) {
            case DateTimeConstant.DEFAULT_DATE_TIME:
                // 解析日期时间格式字符串
                return DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().parse(text);
            case DateTimeConstant.DEFAULT_DATE:
                // 解析日期格式字符串 (时间为 00:00:00)
                return DateFormatterHolder.DEFAULT_DATE_FORMATTER.get().parse(text);
            case DateTimeConstant.DEFAULT_TIME:
                // 解析时间格式字符串 (日期为当前年月日)
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                calendar.setTime(DateFormatterHolder.DEFAULT_TIME_FORMATTER.get().parse(text));
                calendar.set(year, month, date);
                return calendar.getTime();
            default:
                throw new IllegalArgumentException("DateUtil.parseDate(): Unsupported string format! text -> " + text);
        }
    }

    /**
     * 解析日期时间字符串成 Calendar 类型
     *
     * @param text 日期时间字符串
     * @return Calendar 对象
     */
    @SneakyThrows
    public static Calendar parseCalendar(String text) {
        Calendar calendar = Calendar.getInstance();
        switch (pattern(text)) {
            case DateTimeConstant.DEFAULT_DATE_TIME:
                // 解析日期时间格式字符串
                calendar.setTime(DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().parse(text));
                return calendar;
            case DateTimeConstant.DEFAULT_DATE:
                // 解析日期格式字符串 (时间为 00:00:00)
                calendar.setTime(DateFormatterHolder.DEFAULT_DATE_FORMATTER.get().parse(text));
                return calendar;
            case DateTimeConstant.DEFAULT_TIME:
                // 解析时间格式字符串 (日期为当前年月日)
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                calendar.setTime(DateFormatterHolder.DEFAULT_TIME_FORMATTER.get().parse(text));
                calendar.set(year, month, date);
                return calendar;
            default:
                throw new IllegalArgumentException("DateUtil.parseCalendar(): Unsupported string format! text -> " + text);
        }
    }

    /**
     * 获取日期时间字符串的格式
     *
     * @param text 日期时间字符串
     * @return 格式字符串
     */
    private static String pattern(String text) {
        if (DateTimePattern.DEFAULT_DATE_TIME.matcher(text).matches()) {
            return DateTimeConstant.DEFAULT_DATE_TIME;
        } else if (DateTimePattern.DEFAULT_DATE.matcher(text).matches()) {
            return DateTimeConstant.DEFAULT_DATE;
        } else if (DateTimePattern.DEFAULT_TIME.matcher(text).matches()) {
            return DateTimeConstant.DEFAULT_TIME;
        } else {
            return StringConstant.EMPTY;
        }
    }
}
