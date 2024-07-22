package com.cafe.common.lang.date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2023/6/20 15:58
 * @Description: 日期时间区间
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class AbstractPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE = "yyyy-MM-dd";

    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME = "HH:mm:ss";

    /**
     * 开始时间
     */
    private Object start;

    /**
     * 结束时间
     */
    private Object end;

    /**
     * 获取时间区间中的开始时间和结束时间
     *
     * @return times[0] 开始时间, times[1] 结束时间
     */
    public abstract String[] times();

    /**
     * 判断两个时间区间是否相等
     *
     * @param that
     * @return
     */
    public boolean contentEquals(final AbstractPeriod that) {
        // 获取 this 区间的开始时间和结束时间
        String[] thisTimes = this.times();
        // 获取 that 区间的开始时间和结束时间
        String[] thatTimes = that.times();
        // 分别判断开始时间和结束时间
        return Objects.equals(thisTimes[0], thatTimes[0]) && Objects.equals(thisTimes[1], thatTimes[1]);
    }
}
