package com.cafe.common.lang.date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2023/6/20 16:14
 * @Description: java.util.Date 时间区间
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DatePeriod extends AbstractPeriod {

    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    private Date start;

    /**
     * 结束时间
     */
    private Date end;
}
