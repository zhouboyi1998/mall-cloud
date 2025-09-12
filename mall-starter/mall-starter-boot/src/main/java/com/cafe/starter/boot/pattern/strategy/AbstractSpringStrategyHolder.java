package com.cafe.starter.boot.pattern.strategy;

import com.cafe.common.lang.pattern.strategy.AbstractStrategyHolder;
import com.cafe.common.lang.pattern.strategy.IStrategy;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.pattern.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/15 18:11
 * @Description: Spring 策略容器抽象类
 */
@NoArgsConstructor
public abstract class AbstractSpringStrategyHolder<K, S extends IStrategy<K>> extends AbstractStrategyHolder<K, S> {

    /**
     * 策略实现类列表
     */
    @Autowired
    protected List<S> strategyList;

    /**
     * 初始化策略实现类映射
     */
    @PostConstruct
    protected void initStrategyMap() {
        strategyMap = strategyList.stream().collect(Collectors.toMap(S::getKey, Function.identity()));
    }
}
