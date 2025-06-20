package com.cafe.component.grinder.core.filter.support;

import com.cafe.component.grinder.core.context.GrinderContext;
import com.cafe.component.grinder.core.exception.GrinderException;
import com.cafe.component.grinder.core.filter.GrinderFilter;
import com.cafe.component.grinder.core.result.GrinderResult;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter.support
 * @Author: zhouboyi
 * @Date: 2025/6/20 15:15
 * @Description: Grinder 过滤器执行器
 */
@Slf4j
@NoArgsConstructor
public class GrinderFilterProcessor {

    /**
     * 单例对象 (饿汉式)
     */
    private static GrinderFilterProcessor INSTANCE = new GrinderFilterProcessor();

    public static GrinderFilterProcessor instance() {
        return INSTANCE;
    }

    /**
     * 覆盖默认的执行器实例
     */
    public static void setInstance(GrinderFilterProcessor instance) {
        INSTANCE = instance;
    }

    /**
     * 执行前置过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    public void preRoute() throws GrinderException {
        try {
            this.processFilters(GrinderFilterType.PRE_ROUTE);
        } catch (GrinderException e) {
            throw e;
        } catch (Throwable t) {
            throw new GrinderException(500, "Uncaught throwable in process pre filters [" + t.getClass().getName() + "]", t);
        }
    }

    /**
     * 执行路由过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    public void route() throws GrinderException {
        try {
            this.processFilters(GrinderFilterType.ROUTE);
        } catch (GrinderException e) {
            throw e;
        } catch (Throwable t) {
            throw new GrinderException(500, "Uncaught throwable in process route filters [" + t.getClass().getName() + "]", t);
        }
    }

    /**
     * 执行后置过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    public void postRoute() throws GrinderException {
        try {
            this.processFilters(GrinderFilterType.POST_ROUTE);
        } catch (GrinderException e) {
            throw e;
        } catch (Throwable t) {
            throw new GrinderException(500, "Uncaught throwable in process post filters [" + t.getClass().getName() + "]", t);
        }
    }

    /**
     * 执行错误处理过滤器
     */
    public void error() {
        try {
            this.processFilters(GrinderFilterType.ERROR);
        } catch (Throwable t) {
            log.error("{}", t.getMessage(), t);
        }
    }

    /**
     * 根据类型执行过滤器
     *
     * @param filterType 过滤器类型
     * @throws Throwable 异常信息
     */
    public void processFilters(GrinderFilterType filterType) throws Throwable {
        List<GrinderFilter> filters = GrinderFilterHolder.instance().getAll(filterType);
        if (!CollectionUtils.isEmpty(filters)) {
            for (GrinderFilter filter : filters) {
                processFilter(filter);
            }
        }
    }

    /**
     * 执行过滤器
     *
     * @param filter 过滤器
     * @throws GrinderException Grinder 异常信息
     */
    public void processFilter(GrinderFilter filter) throws GrinderException {
        // 获取当前线程上下文
        GrinderContext ctx = GrinderContext.currentContext();
        // 获取过滤器的上下文键
        String contextKey = contextKey(filter);
        try {
            // 执行过滤器
            GrinderResult grinderResult = filter.process();
            // 保存运行结果到上下文中
            ctx.put(contextKey, grinderResult);
            // 如果过滤器运行异常, 抛出异常
            Throwable t = grinderResult.getThrowable();
            if (Objects.nonNull(t)) {
                throw t;
            }
        } catch (Throwable t) {
            // 统一包装异常信息
            if (t instanceof GrinderException) {
                throw (GrinderException) t;
            } else {
                throw new GrinderException(500, "Uncaught throwable in process " + contextKey + " filter [" + t.getClass().getName() + "]", t);
            }
        }
    }

    /**
     * 获取过滤器结果的上下文键
     *
     * @param filter 过滤器
     * @return 上下文键
     */
    private String contextKey(GrinderFilter filter) {
        return filter.filterType() + ":" + filter.filterName();
    }
}
