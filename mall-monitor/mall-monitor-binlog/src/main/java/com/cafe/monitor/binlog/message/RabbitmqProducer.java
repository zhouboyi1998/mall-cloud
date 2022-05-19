package com.cafe.monitor.binlog.message;

import cn.hutool.json.JSONUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog.message
 * @Author: zhouboyi
 * @Date: 2022/5/18 15:03
 * @Description: RabbitMQ 消息生产者
 */
@Component
public class RabbitmqProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitmqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息到 RabbitMQ
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容
     */
    public <T> void convertAndSend(String exchange, String routingKey, T content) {
        // 将消息内容转换为 JSON 字符串格式
        String jsonContent = JSONUtil.toJsonStr(content);
        // 发送消息到 RabbitMQ
        rabbitTemplate.convertAndSend(exchange, routingKey, JSONUtil.toJsonStr(jsonContent));
    }
}
