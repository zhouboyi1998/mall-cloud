package com.cafe.common.lang.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2023/6/20 16:14
 * @Description: java.util.Date 日期时间区间
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DatePeriod extends AbstractPeriod {

    private static final long serialVersionUID = 1L;

    /**
     * java.util.Date 日期时间格式化器
     */
    public static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat(DEFAULT_DATE_TIME));

    /**
     * 开始时间
     */
    private Date start;

    /**
     * 结束时间
     */
    private Date end;

    @Override
    public String[] times() {
        return new String[]{DATE_FORMATTER.get().format(start), DATE_FORMATTER.get().format(end)};
    }
}
