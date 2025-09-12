package com.cafe.common.lang.pattern.strategy;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
        // 策略不存在
        if (Objects.isNull(key)) {
            throw strategyNotFoundException();
        }
        // 获取策略
        return Optional.ofNullable(strategyMap.get(key))
            // 策略未启用
            .orElseThrow(this::strategyNotEnabledException);
    }

    /**
     * 策略不存在异常
     *
     * @return 策略不存在异常
     */
    protected RuntimeException strategyNotFoundException() {
        return new RuntimeException("Strategy not found.");
    }

    /**
     * 策略未启用异常
     *
     * @return 策略未启用异常
     */
    protected RuntimeException strategyNotEnabledException() {
        return new RuntimeException("Strategy not enabled.");
    }
}
