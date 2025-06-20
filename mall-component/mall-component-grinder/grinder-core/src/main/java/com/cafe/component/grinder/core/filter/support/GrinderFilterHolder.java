package com.cafe.component.grinder.core.filter.support;

import com.cafe.component.grinder.core.filter.GrinderFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter.support
 * @Author: zhouboyi
 * @Date: 2025/6/20 15:16
 * @Description: Grinder 过滤器持有者
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GrinderFilterHolder {

    /**
     * 单例对象 (饿汉式)
     */
    private static final GrinderFilterHolder INSTANCE = new GrinderFilterHolder();

    public static GrinderFilterHolder instance() {
        return INSTANCE;
    }

    /**
     * 前置过滤器集合
     */
    private final Map<String, GrinderFilter> preFilters = new ConcurrentHashMap<>();

    /**
     * 路由过滤器集合
     */
    private final Map<String, GrinderFilter> routeFilters = new ConcurrentHashMap<>();

    /**
     * 后置过滤器集合
     */
    private final Map<String, GrinderFilter> postFilters = new ConcurrentHashMap<>();

    /**
     * 错误过滤器集合
     */
    private final Map<String, GrinderFilter> errorFilters = new ConcurrentHashMap<>();

    /**
     * 添加过滤器
     *
     * @param filter 过滤器
     */
    public GrinderFilter put(GrinderFilter filter) {
        switch (filter.filterType()) {
            case PRE_ROUTE:
                return preFilters.putIfAbsent(filter.filterName(), filter);
            case ROUTE:
                return routeFilters.putIfAbsent(filter.filterName(), filter);
            case POST_ROUTE:
                return postFilters.putIfAbsent(filter.filterName(), filter);
            case ERROR:
                return errorFilters.putIfAbsent(filter.filterName(), filter);
            default:
                throw new IllegalArgumentException("Unsupported Grinder Filter Type.");
        }
    }

    /**
     * 批量添加过滤器
     *
     * @param filters 过滤器列表
     */
    public List<GrinderFilter> putAll(List<GrinderFilter> filters) {
        return filters.stream().map(this::put).collect(Collectors.toList());
    }

    /**
     * 根据类型和名称获取对应的过滤器
     *
     * @param filterType 过滤器类型
     * @param filterName 过滤器名称
     * @return 过滤器
     */
    public GrinderFilter get(GrinderFilterType filterType, String filterName) {
        return getAll(filterType).stream()
            .filter(filter -> Objects.equals(filter.filterName(), filterName))
            .findFirst()
            .orElse(null);
    }

    /**
     * 根据类型获取对应的过滤器列表
     *
     * @param filterType 过滤器类型
     * @return 过滤器列表
     */
    public List<GrinderFilter> getAll(GrinderFilterType filterType) {
        switch (filterType) {
            case PRE_ROUTE:
                return preFilters.values().stream().sorted().collect(Collectors.toList());
            case ROUTE:
                return routeFilters.values().stream().sorted().collect(Collectors.toList());
            case POST_ROUTE:
                return postFilters.values().stream().sorted().collect(Collectors.toList());
            case ERROR:
                return errorFilters.values().stream().sorted().collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Unsupported Grinder Filter Type: " + filterType);
        }
    }

    /**
     * 根据类型和名称删除对应的过滤器
     *
     * @param filterType 过滤器类型
     * @param filterName 过滤器名称
     * @return 删除的过滤器
     */
    public GrinderFilter remove(GrinderFilterType filterType, String filterName) {
        switch (filterType) {
            case PRE_ROUTE:
                return preFilters.remove(filterName);
            case ROUTE:
                return routeFilters.remove(filterName);
            case POST_ROUTE:
                return postFilters.remove(filterName);
            case ERROR:
                return errorFilters.remove(filterName);
            default:
                throw new IllegalArgumentException("Unsupported Grinder Filter Type.");
        }
    }

    /**
     * 删除所有过滤器
     */
    public void removeAll() {
        preFilters.clear();
        routeFilters.clear();
        postFilters.clear();
        errorFilters.clear();
    }
}
