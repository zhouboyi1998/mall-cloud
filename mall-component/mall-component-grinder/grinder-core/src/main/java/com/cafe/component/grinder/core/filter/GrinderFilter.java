package com.cafe.component.grinder.core.filter;

import com.cafe.component.grinder.core.filter.support.GrinderFilterStatus;
import com.cafe.component.grinder.core.filter.support.GrinderFilterType;
import com.cafe.component.grinder.core.result.GrinderResult;
import org.springframework.core.Ordered;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter
 * @Author: zhouboyi
 * @Date: 2025/6/20 15:26
 * @Description: Grinder 过滤器抽象类
 */
public abstract class GrinderFilter implements IGrinderFilter, Ordered, Comparable<GrinderFilter> {

    @Override
    public abstract int getOrder();

    @Override
    public int compareTo(GrinderFilter other) {
        return Integer.compare(this.getOrder(), other.getOrder());
    }

    /**
     * 获取当前过滤器的类型
     *
     * @return 过滤器类型
     */
    public abstract GrinderFilterType filterType();

    /**
     * 获取当前过滤器的名称
     *
     * @return 过滤器名称
     */
    public abstract String filterName();

    /**
     * 执行当前过滤器的运行逻辑
     *
     * @return Grinder 运行结果
     */
    public GrinderResult process() {
        // 判断是否需要执行当前过滤器
        if (this.supports()) {
            try {
                // 执行当前过滤器
                Object result = this.run();
                return new GrinderResult(result, GrinderFilterStatus.SUCCESS);
            } catch (Throwable t) {
                return new GrinderResult(t, GrinderFilterStatus.FAILED);
            }
        } else {
            return new GrinderResult(GrinderFilterStatus.SKIPPED);
        }
    }
}
