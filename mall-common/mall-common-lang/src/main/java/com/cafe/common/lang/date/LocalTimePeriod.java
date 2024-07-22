package com.cafe.common.lang.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2024/7/22 10:45
 * @Description: java.time.LocalTime 时间区间
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocalTimePeriod extends AbstractPeriod {

    private static final long serialVersionUID = 1L;

    /**
     * java.util.LocalTime 时间格式化器
     */
    public static final DateTimeFormatter LOCAL_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_TIME);

    /**
     * 开始时间
     */
    private LocalTime start;

    /**
     * 结束时间
     */
    private LocalTime end;

    @Override
    public String[] times() {
        return new String[]{start.format(LOCAL_TIME_FORMATTER), end.format(LOCAL_TIME_FORMATTER)};
    }
}
