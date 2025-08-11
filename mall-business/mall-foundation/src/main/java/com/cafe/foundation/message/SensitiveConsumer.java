package com.cafe.foundation.message;

import com.cafe.common.constant.model.SensitiveConstant;
import com.cafe.common.constant.rocketmq.RocketMQConstant;
import com.cafe.common.json.util.JacksonUtil;
import com.cafe.foundation.facade.SensitiveFacade;
import com.cafe.foundation.model.dto.SensitiveMessageBodyDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.message
 * @Author: zhouboyi
 * @Date: 2025/8/11 11:23
 * @Description: RocketMQ 敏感词消息消费者
 */
@Slf4j
@RequiredArgsConstructor
@Component
@RocketMQMessageListener(
    topic = RocketMQConstant.Topic.SENSITIVE,
    consumerGroup = RocketMQConstant.ConsumerGroup.FOUNDATION,
    // 消费模式: 并发
    consumeMode = ConsumeMode.CONCURRENTLY,
    // 订阅模式: 广播
    messageModel = MessageModel.BROADCASTING
)
public class SensitiveConsumer implements RocketMQListener<String> {

    private final SensitiveFacade sensitiveFacade;

    @Override
    public void onMessage(String message) {
        // 打印成功接收消息的日志
        log.info("SensitiveConsumer.onMessage(): rocketmq message -> {}", message);
        // 获取消息体
        SensitiveMessageBodyDTO messageBody = JacksonUtil.readValue(message, SensitiveMessageBodyDTO.class);
        // 获取消息类型
        String type = messageBody.getType();
        // 根据消息类型处理消息
        switch (type) {
            case SensitiveConstant.MessageType.REINITIALIZE_SENSITIVE_TRIE:
                handleReinitializeSensitiveTrie();
                break;
            case SensitiveConstant.MessageType.ADD_SENSITIVE_WORDS:
                List<Long> sensitiveIds = JacksonUtil.convertValue(messageBody.getContent(), new TypeReference<List<Long>>() {});
                handleAddSensitiveWords(sensitiveIds);
                break;
            default:
                log.warn("SensitiveConsumer.onMessage(): unknown sensitive message type! rocketmq message -> {}", message);
                break;
        }
    }

    /**
     * 重新初始化敏感词字典树
     */
    private void handleReinitializeSensitiveTrie() {
        Instant startTime = Instant.now();
        sensitiveFacade.initSensitiveTrie();
        Instant endTime = Instant.now();
        long duration = Duration.between(startTime, endTime).toMillis();
        log.info("SensitiveConsumer.handleReinitializeSensitiveTrie(): reinitialize sensitive word trie, duration: {} ms", duration);
    }

    /**
     * 添加敏感词到字典树中
     *
     * @param sensitiveIds 敏感词ID列表
     */
    private void handleAddSensitiveWords(List<Long> sensitiveIds) {
        sensitiveFacade.addSensitiveWords(sensitiveIds);
        log.info("SensitiveConsumer.handleAddSensitiveWords(): add sensitive words to sensitive word trie, sensitive ids: {}", sensitiveIds);
    }
}
