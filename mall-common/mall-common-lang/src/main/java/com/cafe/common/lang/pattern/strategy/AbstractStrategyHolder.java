package com.cafe.common.lang.pattern.strategy;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.pattern.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/12 17:47
 * @Description: 策略容器抽象类
 */
public abstract class AbstractStrategyHolder<K, S extends IStrategy<K>> implements IStrategyHolder<K, S> {

    /**
     * 策略实现类映射
     */
    protected Map<K, S> strategyMap;

    @Override
    public S get(K key) {
        return strategyMap.get(key);
    }
}
