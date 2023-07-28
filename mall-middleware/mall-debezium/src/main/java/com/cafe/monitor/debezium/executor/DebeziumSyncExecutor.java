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

    @Override
    public void start() {
        LOGGER.info("DebeziumEngine listener start! Singleton thread pool <{}> start!", ThreadConstant.DEBEZIUM_LISTENER_POOL);
        executorService.execute(debeziumEngine);
    }

    @Override
    public void stop() {
        try {
            LOGGER.warn("DebeziumEngine listener stop!");
            debeziumEngine.close();
        } catch (IOException e) {
            LOGGER.error("DebeziumEngine listener stop error! message -> {}", e.getMessage(), e);
        } finally {
            LOGGER.warn("Singleton thread pool <{}> stop!", ThreadConstant.DEBEZIUM_LISTENER_POOL);
            executorService.shutdown();
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(debeziumEngine, "DebeziumEngine 不可以为空!");
    }
}
