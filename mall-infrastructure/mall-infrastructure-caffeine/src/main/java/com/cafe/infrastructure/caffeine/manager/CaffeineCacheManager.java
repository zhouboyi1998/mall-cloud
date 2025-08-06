package com.cafe.infrastructure.caffeine.manager;

import com.cafe.infrastructure.caffeine.property.CaffeineProperties;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.manager
 * @Author: zhouboyi
 * @Date: 2025/7/31 11:16
 * @Description: Caffeine 缓存管理器
 */
@RequiredArgsConstructor
public class CaffeineCacheManager {

    /**
     * 缓存集合
     */
    private final ConcurrentHashMap<String, Cache<String, Object>> cacheMap = new ConcurrentHashMap<>(16);

    private final CaffeineProperties caffeineProperties;

    /**
     * 保存缓存数据
     *
     * @param cacheName  缓存名称
     * @param cacheKey   缓存键
     * @param cacheValue 缓存值
     * @param expireTime 缓存过期时间
     * @param expireUnit 缓存过期时间单位
     */
    public void put(String cacheName, String cacheKey, Object cacheValue, long expireTime, TimeUnit expireUnit) {
        cacheMap.computeIfAbsent(cacheName, n -> Caffeine.newBuilder()
                // 缓存最大数量
                .maximumSize(caffeineProperties.getDefaultMaximumSize())
                // 缓存过期时间
                .expireAfterWrite(expireTime, expireUnit)
                // 使用手动管理缓存
                .build())
            .put(cacheKey, cacheValue);
    }

    /**
     * 保存缓存数据
     *
     * @param cacheName         缓存名称
     * @param cacheKey          缓存键
     * @param cacheValue        缓存值
     * @param expireTime        缓存过期时间
     * @param expireUnit        缓存过期时间单位
     * @param refreshInterval   缓存刷新间隔
     * @param refreshUnit       缓存刷新间隔单位
     * @param cacheLoadFunction 缓存加载函数
     */
    public void put(String cacheName, String cacheKey, Object cacheValue, long expireTime, TimeUnit expireUnit, long refreshInterval, TimeUnit refreshUnit, Function<String, Object> cacheLoadFunction) {
        cacheMap.computeIfAbsent(cacheName, n -> Caffeine.newBuilder()
                // 缓存最大数量
                .maximumSize(caffeineProperties.getDefaultMaximumSize())
                // 缓存过期时间
                .expireAfterWrite(expireTime, expireUnit)
                // 缓存刷新间隔
                .refreshAfterWrite(refreshInterval, refreshUnit)
                // 使用自动加载缓存 (需要传入缓存加载函数)
                .build(cacheLoadFunction::apply))
            .put(cacheKey, cacheValue);
    }

    /**
     * 获取缓存数据
     *
     * @param cacheName 缓存名称
     * @param cacheKey  缓存键
     * @return 缓存数据
     */
    public Object getIfPresent(String cacheName, String cacheKey) {
        return Optional.ofNullable(cacheMap.get(cacheName)).map(cache -> cache.getIfPresent(cacheKey)).orElse(null);
    }

    /**
     * 获取缓存数据
     *
     * @param cacheName         缓存名称
     * @param cacheKey          缓存键
     * @param cacheLoadFunction 缓存加载函数
     * @return 缓存数据
     */
    public Object get(String cacheName, String cacheKey, Function<String, Object> cacheLoadFunction) {
        return Optional.ofNullable(cacheMap.get(cacheName)).map(cache -> cache.get(cacheKey, cacheLoadFunction)).orElse(null);
    }

    /**
     * 删除缓存数据
     *
     * @param cacheName 缓存名称
     * @param cacheKey  缓存键
     */
    public void invalidate(String cacheName, String cacheKey) {
        Optional.ofNullable(cacheMap.get(cacheName)).ifPresent(cache -> cache.invalidate(cacheKey));
    }

    /**
     * 批量删除缓存数据
     *
     * @param cacheName 缓存名称
     */
    public void invalidateAll(String cacheName) {
        Optional.ofNullable(cacheMap.get(cacheName)).ifPresent(Cache::invalidateAll);
    }
}
