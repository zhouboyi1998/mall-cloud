package com.cafe.common.lang.pattern.strategy;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.pattern.strategy
 * @Author: zhouboyi
 * @Date: 2025/9/12 16:59
 * @Description: 策略接口
 */
public interface IStrategy<K> {

    /**
     * 获取策略唯一标识符
     *
     * @return 策略唯一标识符
     */
    K getKey();
}
