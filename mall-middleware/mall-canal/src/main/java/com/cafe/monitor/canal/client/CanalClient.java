package com.cafe.monitor.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

import com.alibaba.otter.canal.protocol.Message;
import com.cafe.monitor.canal.handler.CanalEntryHandler;
import com.cafe.monitor.canal.property.CanalProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.client
 * @Author: zhouboyi
 * @Date: 2022/7/13 20:53
 * @Description: Canal 客户端
 */
@Component
public class CanalClient implements InitializingBean {

    private final CanalProperties canalProperties;

    private final CanalEntryHandler canalEntryHandler;

    @Autowired
    public CanalClient(
        CanalProperties canalProperties,
        CanalEntryHandler canalEntryHandler
    ) {
        this.canalProperties = canalProperties;
        this.canalEntryHandler = canalEntryHandler;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
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
                // 获取 batch id
                long batchId = message.getId();
                // 获取 batch 数据的数量
                int size = message.getEntries().size();
                // 如果没有数据
                if (batchId == -1 || size == 0) {
                    try {
                        // 线程休眠 2 秒
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 如果有数据, 处理数据
                    canalEntryHandler.handle(message.getEntries());
                }
                // 根据 batch id 进行确认, 小于或等于该 batch id 的 message都会被 ack
                connector.ack(batchId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }
}
