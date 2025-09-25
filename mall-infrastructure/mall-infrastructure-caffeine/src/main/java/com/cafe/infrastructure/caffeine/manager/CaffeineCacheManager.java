package com.cafe.infrastructure.caffeine.manager;

import com.cafe.infrastructure.caffeine.property.CaffeineProperties;
import com.cafe.infrastructure.caffeine.support.ExpirePolicy;
import com.cafe.infrastructure.caffeine.support.InvalidCacheLoader;
import com.cafe.infrastructure.caffeine.support.MaximumPolicy;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Weigher;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
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
     * 构建缓存
     *
     * @param initialCapacity 缓存初始容量
     * @param maximumPolicy   缓存最大限度策略
     * @param maximumSize     缓存最大容量
     * @param maximumWeight   缓存最大权重
     * @param weigher         缓存权重计算器
     * @param expireTime      缓存过期时间
     * @param expireUnit      缓存过期时间单位
     * @param expirePolicy    缓存过期策略
     * @param refreshInterval 缓存刷新间隔
     * @param refreshUnit     缓存刷新间隔单位
     * @param cacheLoader     缓存加载器
     * @return 缓存
     */
    public Cache<String, Object> build(
        Integer initialCapacity, MaximumPolicy maximumPolicy, Long maximumSize, Long maximumWeight, Weigher<String, Object> weigher,
        Long expireTime, TimeUnit expireUnit, ExpirePolicy expirePolicy,
        Long refreshInterval, TimeUnit refreshUnit, CacheLoader<String, Object> cacheLoader
    ) {
        // 新建 Caffeine 缓存构造器
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
            // 设置缓存初始容量
            .initialCapacity(Optional.ofNullable(initialCapacity).filter(s -> s >= 0).orElse(caffeineProperties.getCapacity().getInitialCapacity()));

        // 设置缓存最大限度
        switch (maximumPolicy) {
            case MAXiMUM_SIZE:
                // 设置缓存最大容量
                caffeine.maximumSize(Optional.ofNullable(maximumSize).filter(s -> s >= 0L).orElse(caffeineProperties.getCapacity().getMaximumSize()));
                break;
            case MAXIMUM_WEIGHT:
                // 设置缓存最大权重
                caffeine.maximumWeight(Optional.ofNullable(maximumWeight).filter(s -> s >= 0L).orElse(caffeineProperties.getCapacity().getMaximumWeight()))
                    // 设置缓存权重计算器
                    .weigher(weigher);
                break;
        }

        // 设置缓存过期时间
        switch (expirePolicy) {
            case EXPIRE_AFTER_WRITE:
                caffeine.expireAfterWrite(expireTime, expireUnit);
                break;
            case EXPIRE_AFTER_ACCESS:
                caffeine.expireAfterAccess(expireTime, expireUnit);
                break;
        }

        // 构造缓存
        if (Objects.nonNull(refreshInterval) && refreshInterval >= 0L && Objects.nonNull(refreshUnit) && Objects.nonNull(cacheLoader) && !(cacheLoader instanceof InvalidCacheLoader)) {
            // 设置缓存刷新间隔
            return caffeine.refreshAfterWrite(refreshInterval, refreshUnit)
                // 构造自动刷新缓存 (每隔一段时间通过缓存加载器刷新缓存数据)
                .build(cacheLoader);
        } else {
            // 构造普通缓存
            return caffeine.build();
        }
    }

    /**
     * 保存缓存数据
     *
     * @param cacheName         缓存名称
     * @param cacheKey          缓存键
     * @param cacheValue        缓存值
     * @param initialCapacity   缓存初始容量
     * @param maximumPolicy     缓存最大限度策略
     * @param maximumSize       缓存最大容量
     * @param maximumWeight     缓存最大权重
     * @param weigher           缓存权重计算器
     * @param expireTime        缓存过期时间
     * @param expireUnit        缓存过期时间单位
     * @param expirePolicy      缓存过期策略
     * @param refreshInterval   缓存刷新间隔
     * @param refreshUnit       缓存刷新间隔单位
     * @param cacheLoadFunction 缓存加载函数
     */
    public void put(
        String cacheName, String cacheKey, Object cacheValue,
        Integer initialCapacity, MaximumPolicy maximumPolicy, Long maximumSize, Long maximumWeight, Weigher<String, Object> weigher,
        Long expireTime, TimeUnit expireUnit, ExpirePolicy expirePolicy,
        Long refreshInterval, TimeUnit refreshUnit, Function<String, Object> cacheLoadFunction
    ) {
        put(cacheName, cacheKey, cacheValue, initialCapacity, maximumPolicy, maximumSize, maximumWeight, weigher, expireTime, expireUnit, expirePolicy, refreshInterval, refreshUnit, (CacheLoader<String, Object>) cacheLoadFunction::apply);
    }

    /**
     * 保存缓存数据
     *
     * @param cacheName       缓存名称
     * @param cacheKey        缓存键
     * @param cacheValue      缓存值
     * @param initialCapacity 缓存初始容量
     * @param maximumPolicy   缓存最大限度策略
     * @param maximumSize     缓存最大容量
     * @param maximumWeight   缓存最大权重
     * @param weigher         缓存权重计算器
     * @param expireTime      缓存过期时间
     * @param expireUnit      缓存过期时间单位
     * @param expirePolicy    缓存过期策略
     * @param refreshInterval 缓存刷新间隔
     * @param refreshUnit     缓存刷新间隔单位
     * @param cacheLoader     缓存加载器
     */
    public void put(
        String cacheName, String cacheKey, Object cacheValue,
        Integer initialCapacity, MaximumPolicy maximumPolicy, Long maximumSize, Long maximumWeight, Weigher<String, Object> weigher,
        Long expireTime, TimeUnit expireUnit, ExpirePolicy expirePolicy,
        Long refreshInterval, TimeUnit refreshUnit, CacheLoader<String, Object> cacheLoader
    ) {
        cacheMap.computeIfAbsent(cacheName, n -> build(initialCapacity, maximumPolicy, maximumSize, maximumWeight, weigher, expireTime, expireUnit, expirePolicy, refreshInterval, refreshUnit, cacheLoader)).put(cacheKey, cacheValue);
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
    public void invalidateAll(String cacheName, Iterable<String> cacheKeys) {
        Optional.ofNullable(cacheMap.get(cacheName)).ifPresent(cache -> cache.invalidateAll(cacheKeys));
    }

    /**
     * 删除所有缓存数据
     *
     * @param cacheName 缓存名称
     */
    public void invalidateAll(String cacheName) {
        Optional.ofNullable(cacheMap.get(cacheName)).ifPresent(Cache::invalidateAll);
    }
}
