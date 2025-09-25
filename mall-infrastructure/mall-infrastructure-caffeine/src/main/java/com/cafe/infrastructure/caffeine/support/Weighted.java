package com.cafe.infrastructure.caffeine.support;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.support
 * @Author: zhouboyi
 * @Date: 2025/9/26 11:46
 * @Description: 缓存对象权重计算接口
 */
public interface Weighted {

    /**
     * 获取权重
     *
     * @return 权重
     */
    int getWeight();
}
