package com.cafe.component.grinder.core.result;

import com.cafe.component.grinder.core.filter.support.GrinderFilterStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.result
 * @Author: zhouboyi
 * @Date: 2025/6/23 11:24
 * @Description: Grinder 运行结果
 */
@Getter
@Setter
public final class GrinderResult {

    private Object result;

    private Throwable throwable;

    private GrinderFilterStatus status;

    public GrinderResult(Object result, GrinderFilterStatus status) {
        this.result = result;
        this.status = status;
    }

    public GrinderResult(Throwable throwable, GrinderFilterStatus status) {
        this.throwable = throwable;
        this.status = status;
    }

    public GrinderResult(GrinderFilterStatus status) {
        this.status = status;
    }
}
