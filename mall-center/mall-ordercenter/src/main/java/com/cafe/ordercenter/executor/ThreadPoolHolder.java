package com.cafe.ordercenter.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.executor
 * @Author: zhouboyi
 * @Date: 2025/3/24 21:42
 * @Description: 线程池持有者
 */
@Slf4j
@Component
public class ThreadPoolHolder {

    /**
     * 提交订单线程池 (IO 密集型)
     */
    public static ThreadPoolExecutor SUBMIT_ORDER_THREAD_POOL;

    @PostConstruct
    public void initSubmitOrderThreadPool() {
        // 创建线程池
        SUBMIT_ORDER_THREAD_POOL = new ThreadPoolExecutor(
            // 核心线程数
            Runtime.getRuntime().availableProcessors() * 2,
            // 最大线程数
            Runtime.getRuntime().availableProcessors() * 4,
            // 多余的空闲线程存活时间
            60, TimeUnit.SECONDS,
            // 阻塞队列容量
            new LinkedBlockingQueue<>(2048),
            // 拒绝策略: 如果线程池已满, 直接在调用方的线程中执行任务
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
        // 预热线程池
        SUBMIT_ORDER_THREAD_POOL.prestartAllCoreThreads();
        // 打印线程池信息
        log.info("ThreadPoolHolder.initSubmitOrderThreadPool(): corePoolSize -> {}, maxPoolSize -> {}", SUBMIT_ORDER_THREAD_POOL.getCorePoolSize(), SUBMIT_ORDER_THREAD_POOL.getMaximumPoolSize());
    }

    @PreDestroy
    public void destroySubmitOrderThreadPool() {
        // 关闭线程池
        SUBMIT_ORDER_THREAD_POOL.shutdown();
    }
}
