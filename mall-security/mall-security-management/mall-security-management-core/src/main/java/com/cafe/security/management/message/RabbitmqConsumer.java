package com.cafe.security.management.message;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.RabbitmqExchangeName;
import com.cafe.common.constant.RabbitmqQueueName;
import com.cafe.common.constant.RabbitmqRoutingKey;
import com.cafe.common.constant.StringConstant;
import com.cafe.common.security.service.ResourceService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.message
 * @Author: zhouboyi
 * @Date: 2022/5/18 14:56
 * @Description: RabbitMQ 消息消费者 (接收数据库表修改消息)
 */
@Component
public class RabbitmqConsumer {

    private ResourceService resourceService;

    @Autowired
    public RabbitmqConsumer(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 监听 RabbitMQ 接收消息
     *
     * @param content 消息内容
     */
    @RabbitListeners(value = {
        @RabbitListener(
            bindings = @QueueBinding(
                value = @Queue(
                    value = RabbitmqQueueName.ROLE_MENU_RELATION,
                    durable = StringConstant.TRUE,
                    autoDelete = StringConstant.FALSE
                ),
                exchange = @Exchange(value = RabbitmqExchangeName.BINLOG),
                key = {RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU_RELATION}
            )
        ),
        @RabbitListener(
            bindings = @QueueBinding(
                value = @Queue(
                    value = RabbitmqQueueName.ROLE_MENU_RELATION,
                    durable = StringConstant.TRUE,
                    autoDelete = StringConstant.FALSE
                ),
                exchange = @Exchange(value = RabbitmqExchangeName.CANAL),
                key = {RabbitmqRoutingKey.CANAL_TO_ROLE_MENU_RELATION}
            )
        )
    })
    public void listener(String content) {
        // JSONStr 转换为 JSONArray 再转换为 List
        List<Long> ids = JSONUtil.parseArray(content).toList(Long.class);
        // 更新 Redis 中的数据
        resourceService.updateRelationData(ids);
    }
}
