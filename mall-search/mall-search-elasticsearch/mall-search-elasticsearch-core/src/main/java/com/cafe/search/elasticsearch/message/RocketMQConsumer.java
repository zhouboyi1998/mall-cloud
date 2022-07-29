package com.cafe.search.elasticsearch.message;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rocketmq.constant.RocketMQConsumerGroup;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.message
 * @Author: zhouboyi
 * @Date: 2022/7/28 23:33
 * @Description: RocketMQ 消费者 (实时同步 MySQL 数据到 ElasticSearch 中)
 */
@Component
@RocketMQMessageListener(consumerGroup = RocketMQConsumerGroup.GROUP_ONE, topic = RocketMQTopic.CANAL_TO_ES_GOODS_RELATION)
public class RocketMQConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("消息内容: " + message);

        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更的类型
        String operation = content.get(MonitorConstant.OPERATION).toString();
        // 获取变更的表
        String tableName = content.get(MonitorConstant.TABLE_NAME).toString();

        System.out.println("operation: " + operation);
        System.out.println("tableName: " + tableName);
    }
}
