package com.cafe.id.service;

import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.id.property.IDProperties;
import com.cafe.id.support.IDGenerator;
import com.cafe.starter.boot.model.exception.BusinessException;
import com.cafe.starter.boot.pattern.strategy.AbstractSpringStrategyHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service
 * @Author: zhouboyi
 * @Date: 2025/7/8 17:45
 * @Description: 分布式ID业务策略容器
 */
@RequiredArgsConstructor
@Component
public class IDServiceStrategyHolder extends AbstractSpringStrategyHolder<IDGenerator, IDService> {

    /**
     * 分布式ID配置
     */
    private final IDProperties idProperties;

    /**
     * 根据策略名称获取策略实现类
     *
     * @param name 策略名称
     * @return 策略实现类
     */
    public IDService get(String name) {
        return Objects.isNull(name) ? get() : get(IDGenerator.get(name));
    }

    /**
     * 获取默认策略实现类
     *
     * @return 默认策略实现类
     */
    public IDService get() {
        return get(defaultKey());
    }

    /**
     * 获取默认策略唯一标识符
     *
     * @return 默认策略唯一标识符
     */
    public IDGenerator defaultKey() {
        return idProperties.getDefaultGenerator();
    }

    @Override
    protected RuntimeException strategyNotFoundException() {
        return new BusinessException(HttpStatusEnum.ID_GENERATOR_NOT_FOUND);
    }

    @Override
    protected RuntimeException strategyNotEnabledException() {
        return new BusinessException(HttpStatusEnum.ID_GENERATOR_NOT_ENABLED);
    }
}
