package com.cafe.monitor.debezium.executor;

import com.cafe.common.constant.thread.ThreadConstant;
import com.cafe.monitor.debezium.thread.SingletonThreadPool;
import io.debezium.engine.DebeziumEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.debezium.executor
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:34
 * @Description: Debezium 同步执行器
 */
public class DebeziumSyncExecutor implements InitializingBean, SmartLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebeziumSyncExecutor.class);

    private final ExecutorService executorService = SingletonThreadPool.INSTANCE.getInstance();

    private DebeziumEngine<?> debeziumEngine;

    public void setDebeziumEngine(DebeziumEngine<?> debeziumEngine) {
        this.debeziumEngine = debeziumEngine;
    }

    /**
     * Debezium 启动
     */
    @Override
    public void start() {
        LOGGER.info("DebeziumSyncExecutor.start(): DebeziumEngine start! Singleton thread pool <{}> start!", ThreadConstant.DEBEZIUM_LISTENER_POOL);
        executorService.execute(debeziumEngine);
    }

    /**
     * Debezium 关闭
     */
    @Override
    public void stop() {
        try {
            LOGGER.warn("DebeziumSyncExecutor.stop(): DebeziumEngine stop!");
            debeziumEngine.close();
        } catch (IOException e) {
            LOGGER.error("DebeziumSyncExecutor.stop(): DebeziumEngine stop error! message -> {}", e.getMessage(), e);
        } finally {
            LOGGER.warn("DebeziumSyncExecutor.stop(): Singleton thread pool <{}> stop!", ThreadConstant.DEBEZIUM_LISTENER_POOL);
            executorService.shutdown();
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void afterPropertiesSet() {
        // 判断 DebeziumEngine 是否为 null
        Assert.notNull(debeziumEngine, "DebeziumEngine can not be null!");
    }
}
