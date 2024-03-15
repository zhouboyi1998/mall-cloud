package com.cafe.debezium.thread;

import com.cafe.common.constant.pool.IntegerConstant;
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
            IntegerConstant.EIGHT, IntegerConstant.SIXTEEN, IntegerConstant.SIXTY, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(IntegerConstant.TWO_HUNDRED_AND_FIFTY_SIX),
            new ThreadFactoryBuilder().setNameFormat(ThreadConstant.DEBEZIUM_LISTENER_POOL).build(),
            new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    public ExecutorService getInstance() {
        return executorService;
    }
}
