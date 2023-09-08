package com.cafe.common.lang.date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2023/6/20 15:58
 * @Description: 时间区间
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class AbstractPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认时间格式
     */
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * java.util.Date 时间格式化器
     */
    public static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN));

    /**
     * java.util.LocalDateTime 时间格式化器
     */
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    /**
     * 开始时间
     */
    private Object start;

    /**
     * 结束时间
     */
    private Object end;

    /**
     * 判断两个时间区间是否相等
     *
     * @param other
     * @return
     */
    public boolean contentEquals(final AbstractPeriod other) {
        // 获取 this 区间的开始时间和结束时间
        String[] thisTimes = times(this);
        // 获取 other 区间的开始时间和结束时间
        String[] otherTimes = times(other);
        // 分别判断开始时间和结束时间
        return Objects.equals(thisTimes[0], otherTimes[0]) && Objects.equals(thisTimes[1], otherTimes[1]);
    }

    /**
     * 获取时间区间的开始时间和结束时间
     *
     * @param period
     * @return
     */
    private String[] times(AbstractPeriod period) {
        // times[0] 开始时间, times[1] 结束时间
        String[] times = new String[2];
        if (period instanceof DatePeriod) {
            times[0] = DATE_FORMATTER.get().format(((DatePeriod) period).getStart());
            times[1] = DATE_FORMATTER.get().format(((DatePeriod) period).getEnd());
        } else if (period instanceof LocalDateTimePeriod) {
            times[0] = ((LocalDateTime) period.getStart()).format(LOCAL_DATE_TIME_FORMATTER);
            times[1] = ((LocalDateTime) period.getEnd()).format(LOCAL_DATE_TIME_FORMATTER);
        } else {
            throw new RuntimeException("Could not format the period!");
        }
        return times;
    }
}
