package com.cafe.starter.boot.strategy;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/12 17:47
 * @Description: 策略容器抽象类
 */
@NoArgsConstructor
public abstract class AbstractStrategyHolder<K, S extends IStrategy<K>> {

    /**
     * 策略实现类列表
     */
    @Autowired
    protected List<S> strategyList;

    /**
     * 策略实现类映射
     */
    protected Map<K, S> strategyMap;

    /**
     * 初始化策略实现类映射
     */
    @PostConstruct
    public void initServiceMap() {
        strategyMap = strategyList.stream().collect(Collectors.toMap(S::getKey, Function.identity()));
    }

    /**
     * 根据策略唯一标识符获取策略实现类
     *
     * @param strategyKey 策略唯一标识符
     * @return 策略实现类
     */
    public S getStrategy(K strategyKey) {
        return strategyMap.get(strategyKey);
    }

    /**
     * 根据策略名称获取策略实现类
     *
     * @param strategyName 策略名称
     * @return 策略实现类
     */
    public S getStrategy(String strategyName) {
        return getStrategy(getStrategyKey(strategyName));
    }

    /**
     * 获取默认策略实现类
     *
     * @return 默认策略实现类
     */
    public S getStrategy() {
        return getStrategy(getDefaultStrategyKey());
    }

    /**
     * 根据策略名称获取策略唯一标识符
     *
     * @param strategyName 策略名称
     * @return 策略唯一标识符
     */
    public abstract K getStrategyKey(String strategyName);

    /**
     * 获取默认策略唯一标识符
     *
     * @return 默认策略唯一标识符
     */
    public abstract K getDefaultStrategyKey();
}
