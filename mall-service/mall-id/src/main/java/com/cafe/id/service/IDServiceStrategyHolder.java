package com.cafe.id.service;

import com.cafe.id.property.IDProperties;
import com.cafe.id.support.IDGenerator;
import com.cafe.starter.boot.strategy.AbstractStrategyHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service
 * @Author: zhouboyi
 * @Date: 2025/7/8 17:45
 * @Description: 分布式ID业务策略容器
 */
@RequiredArgsConstructor
@Component
public class IDServiceStrategyHolder extends AbstractStrategyHolder<IDGenerator, IDService> {

    /**
     * 分布式ID配置
     */
    private final IDProperties idProperties;

    @Override
    public IDGenerator getStrategyKey(String strategyName) {
        return Optional.ofNullable(strategyName).map(IDGenerator::findByName).orElse(getDefaultStrategyKey());
    }

    @Override
    public IDGenerator getDefaultStrategyKey() {
        return idProperties.getDefaultGenerator();
    }
}
