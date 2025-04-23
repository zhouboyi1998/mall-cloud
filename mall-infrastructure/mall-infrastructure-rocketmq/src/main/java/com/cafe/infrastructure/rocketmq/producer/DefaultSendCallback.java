package com.cafe.infrastructure.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.rocketmq.producer
 * @Author: zhouboyi
 * @Date: 2025/4/22 15:38
 * @Description: RocketMQ 默认消息发送回调处理器
 */
@Slf4j
@Component
public class DefaultSendCallback implements SendCallback {

    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("DefaultSendCallback.onSuccess(): message send success, send result -> {}", sendResult);
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("DefaultSendCallback.onException(): message send failed, throwable message -> {}", throwable.getMessage(), throwable);
    }
}
