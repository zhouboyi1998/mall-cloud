package com.cafe.common.lang.pattern.strategy;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.pattern.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/19 14:43
 * @Description: 可变策略容器
 */
public class MutableStrategyHolder<K, S extends IStrategy<K>> extends ConcurrentHashMap<K, S> implements IMutableStrategyHolder<K, S> {

    @Override
    public S put(S strategy) {
        return put(strategy.getKey(), strategy);
    }

    @Override
    public S putIfAbsent(S strategy) {
        return putIfAbsent(strategy.getKey(), strategy);
    }

    @Override
    public void putAll(Collection<S> strategies) {
        if (CollectionUtils.isEmpty(strategies)) {
            return;
        }
        putAll(strategies.stream().collect(Collectors.toMap(S::getKey, Function.identity())));
    }
}
