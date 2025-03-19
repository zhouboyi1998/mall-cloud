package com.cafe.common.lang.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2023/6/16 18:29
 * @Description: java.time.LocalDateTime 日期时间区间
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocalDateTimePeriod extends AbstractPeriod {

    private static final long serialVersionUID = 1L;

    /**
     * java.util.LocalDateTime 日期时间格式化器
     */
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME);

    /**
     * 开始时间
     */
    private LocalDateTime start;

    /**
     * 结束时间
     */
    private LocalDateTime end;

    @Override
    public String[] times() {
        return new String[]{start.format(LOCAL_DATE_TIME_FORMATTER), end.format(LOCAL_DATE_TIME_FORMATTER)};
    }
}
