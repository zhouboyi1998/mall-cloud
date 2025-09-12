package com.cafe.common.lang.pattern.strategy;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.pattern.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/16 11:25
 * @Description: 策略容器接口
 */
public interface IStrategyHolder<K, S extends IStrategy<K>> {

    /**
     * 根据策略唯一标识符获取策略实现类
     *
     * @param key 策略唯一标识符
     * @return 策略实现类
     */
    S get(K key);
}
