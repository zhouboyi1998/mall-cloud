package com.cafe.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.cafe.canal.handler.CanalEntryHandler;
import com.cafe.canal.property.CanalProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;

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

    private final CanalProperties canalProperties;

    private final CanalEntryHandler canalEntryHandler;

    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    @SneakyThrows
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
            // 订阅的数据表
            connector.subscribe(canalProperties.getSubscribeTable());
            // 回滚到未进行 ack 的地方，下次 fetch 的时候，可以从最后一个没有 ack 的 message 开始
            connector.rollback();
            while (true) {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(canalProperties.getBatchSize());
                // 获取批次号
                long batchId = message.getId();
                // 获取该批次数据
                List<CanalEntry.Entry> entryList = message.getEntries();
                // 获取该批次数据的大小
                int size = entryList.size();
                // 如果没有数据
                if (batchId == -1 || size == 0) {
                    // 线程休眠 2 秒
                    Thread.sleep(2000);
                } else {
                    // 处理变更数据
                    canalEntryHandler.handle(entryList);
                }
                // 根据批次号进行确认, 小于或等于该批次号的 message 都会被 ack
                connector.ack(batchId);
            }
        } finally {
            // 关闭连接
            connector.disconnect();
        }
    }
}
