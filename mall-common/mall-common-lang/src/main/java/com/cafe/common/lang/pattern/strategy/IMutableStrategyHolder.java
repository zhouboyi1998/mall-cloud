package com.cafe.common.lang.pattern.strategy;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.pattern.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/19 15:46
 * @Description: 可变策略容器接口
 */
public interface IMutableStrategyHolder<K, S extends IStrategy<K>> extends IStrategyHolder<K, S>, ConcurrentMap<K, S> {

    /**
     * 添加策略实现类
     *
     * @param strategy 策略实现类
     */
    S put(S strategy);

    /**
     * 添加策略实现类
     *
     * @param strategy 策略实现类
     */
    S putIfAbsent(S strategy);

    /**
     * 批量添加策略实现类
     *
     * @param strategies 策略实现类列表
     */
    void putAll(Collection<S> strategies);
}
