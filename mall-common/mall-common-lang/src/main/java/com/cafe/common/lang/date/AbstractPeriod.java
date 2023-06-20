package com.cafe.common.lang.date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
     * 开始时间
     */
    private Object start;

    /**
     * 结束时间
     */
    private Object end;
}
