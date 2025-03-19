package com.cafe.common.lang.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2024/7/22 10:45
 * @Description: java.time.LocalDate 日期区间
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocalDatePeriod extends AbstractPeriod {

    private static final long serialVersionUID = 1L;

    /**
     * java.util.LocalDate 日期格式化器
     */
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE);

    /**
     * 开始时间
     */
    private LocalDate start;

    /**
     * 结束时间
     */
    private LocalDate end;

    @Override
    public String[] times() {
        return new String[]{start.format(LOCAL_DATE_FORMATTER), end.format(LOCAL_DATE_FORMATTER)};
    }
}
