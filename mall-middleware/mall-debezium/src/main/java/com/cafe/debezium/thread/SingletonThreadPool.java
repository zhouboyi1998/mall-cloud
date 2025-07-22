package com.cafe.debezium.thread;

import com.cafe.common.constant.thread.ThreadConstant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.debezium.thread
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:38
 * @Description: 单例线程池
 */
public enum SingletonThreadPool {

    /**
     * 实例
     */
    INSTANCE;

    /**
     * 线程池
     */
    private final ExecutorService executorService;

    SingletonThreadPool() {
        executorService = new ThreadPoolExecutor(
            // 核心线程数
            Runtime.getRuntime().availableProcessors(),
            // 最大线程数
            Runtime.getRuntime().availableProcessors() * 2,
            // 多余的空闲线程存活时间
            60, TimeUnit.SECONDS,
            // 阻塞队列容量
            new ArrayBlockingQueue<>(256),
            // 线程工厂: 设置线程名称格式
            new ThreadFactoryBuilder().setNameFormat(ThreadConstant.DEBEZIUM_LISTENER_POOL).build(),
            // 拒绝策略: 如果线程池已满, 直接丢弃任务
            new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    public ExecutorService getInstance() {
        return executorService;
    }
}
