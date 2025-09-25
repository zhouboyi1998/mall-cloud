package com.cafe.infrastructure.caffeine.support;

import com.github.benmanes.caffeine.cache.CacheLoader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.support
 * @Author: zhouboyi
 * @Date: 2025/9/25 10:55
 * @Description: 无效的缓存加载器
 */
public final class InvalidCacheLoader implements CacheLoader<String, Object> {

    @Override
    public @Nullable Object load(@NonNull String key) throws Exception {
        throw new UnsupportedOperationException("InvalidCacheLoader should not be used to load cache values.");
    }
}
