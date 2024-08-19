package com.cafe.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import com.cafe.canal.handler.CanalEntryHandler;
import com.cafe.canal.property.CanalProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.canal.client
 * @Author: zhouboyi
 * @Date: 2022/7/13 20:53
 * @Description: Canal 客户端
 */
@RequiredArgsConstructor
@Component
public class CanalClient implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanalClient.class);

    private final CanalProperties canalProperties;

    private final CanalEntryHandler canalEntryHandler;

    @Override
    public void afterPropertiesSet() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(
            new InetSocketAddress(canalProperties.getHost(), canalProperties.getPort()),
            canalProperties.getDestination(),
            canalProperties.getUsername(),
            canalProperties.getPassword()
        );

        try {
            // 打开连接
            connector.connect();
            // 订阅数据库全部表
            connector.subscribe(".*\\..*");
            // 回滚到未进行 ack 的地方，下次 fetch 的时候，可以从最后一个没有 ack 的 message 开始
            connector.rollback();
            while (true) {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(canalProperties.getBatchSize());
                // 获取批次号
                long batchId = message.getId();
                // 获取该批次数据的数量
                int size = message.getEntries().size();
                // 如果没有数据
                if (batchId == -1 || size == 0) {
                    try {
                        // 线程休眠 2 秒
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        LOGGER.error("CanalClient.afterPropertiesSet(): Thread sleep error! message -> {}", e.getMessage(), e);
                    }
                } else {
                    // 如果有数据, 处理数据
                    canalEntryHandler.handle(message.getEntries());
                }
                // 根据批次号进行确认, 小于或等于该批次号的 message 都会被 ack
                connector.ack(batchId);
            }
        } catch (Exception e) {
            LOGGER.error("CanalClient.afterPropertiesSet(): Canal fail to connect Database! message -> {}", e.getMessage(), e);
        } finally {
            // 关闭连接
            connector.disconnect();
        }
    }
}
