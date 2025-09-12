package com.cafe.common.lang.pattern.strategy;

import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.pattern.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/19 10:17
 * @Description: SPI 策略容器抽象类
 */
public abstract class AbstractSPIStrategyHolder<K, S extends IStrategy<K>> extends AbstractStrategyHolder<K, S> {

    public AbstractSPIStrategyHolder() {
        initStrategyMap();
    }

    /**
     * 初始化策略实现类映射
     */
    protected void initStrategyMap() {
        ServiceLoader<S> serviceLoader = ServiceLoader.load(strategyClass());
        strategyMap = StreamSupport.stream(serviceLoader.spliterator(), false)
            .collect(Collectors.toMap(S::getKey, Function.identity()));
    }

    /**
     * 获取策略接口 Class 对象
     *
     * @return 策略接口 Class 对象
     */
    protected abstract Class<S> strategyClass();
}
