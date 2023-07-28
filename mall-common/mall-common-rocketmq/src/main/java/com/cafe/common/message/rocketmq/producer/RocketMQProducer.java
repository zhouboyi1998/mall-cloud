package com.cafe.common.message.rocketmq.producer;

import cn.hutool.json.JSONUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.message.rocketmq.producer
 * @Author: zhouboyi
 * @Date: 2023/4/24 21:58
 * @Description: RocketMQ 消息生产者
 */
@Component
public class RocketMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQProducer.class);

    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    public RocketMQProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /**
     * 发送消息到 RocketMQ
     *
     * @param topic   主题
     * @param content 消息内容
     */
    public <T> void convertAndSend(String topic, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String message = JSONUtil.toJsonStr(content);
        // 打印日志
        LOGGER.info("RocketMQProducer.convertAndSend(): rocketmq message -> {}", message);
        // 发送消息到 RocketMQ
        rocketMQTemplate.convertAndSend(topic, message);
    }
}
