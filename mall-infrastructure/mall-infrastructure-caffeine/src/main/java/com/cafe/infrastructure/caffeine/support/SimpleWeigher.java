package com.cafe.infrastructure.caffeine.support;

import com.github.benmanes.caffeine.cache.Weigher;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.support
 * @Author: zhouboyi
 * @Date: 2025/9/26 10:50
 * @Description: 简单缓存权重计算器
 */
public class SimpleWeigher implements Weigher<String, Object> {

    /**
     * 默认权重
     */
    protected static final int DEFAULT_WEIGHT = 8;

    @Override
    public @NonNegative int weigh(@NonNull String key, @NonNull Object value) {
        return weigh(key) + weigh(value);
    }

    protected int weigh(Object value) {
        // String 格式权重为字符串长度
        if (value instanceof String) {
            return ((String) value).length();
        }

        if (value instanceof Collection) {
            Collection<?> collection = (Collection<?>) value;
            if (CollectionUtils.isEmpty(collection)) {
                return DEFAULT_WEIGHT;
            } else {
                // Collection 对象的权重为: 长度 * 第一个元素的权重
                return collection.size() * weigh(collection.iterator().next());
            }
        }

        if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            if (CollectionUtils.isEmpty(map)) {
                return DEFAULT_WEIGHT;
            } else {
                Map.Entry<?, ?> entry = map.entrySet().iterator().next();
                // Map 对象的权重为: 长度 * (第一个元素的键的权重 + 第一个元素的值的权重)
                return map.size() * (weigh(entry.getKey()) + weigh(entry.getValue()));
            }
        }

        // 实现了权重计算接口的缓存对象, 使用其自定义的权重值
        if (value instanceof Weighted) {
            return ((Weighted) value).getWeight();
        }

        // 默认权重
        return DEFAULT_WEIGHT;
    }
}
