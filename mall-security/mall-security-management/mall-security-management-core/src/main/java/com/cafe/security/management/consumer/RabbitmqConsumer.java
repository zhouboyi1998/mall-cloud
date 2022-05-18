package com.cafe.security.management.consumer;

import cn.hutool.json.JSONUtil;
import com.cafe.common.security.service.ResourceService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.consumer
 * @Author: zhouboyi
 * @Date: 2022/5/18 14:56
 * @Description: RabbitMQ 消息消费者
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
    @RabbitListener(
        bindings = @QueueBinding(
            value = @Queue(value = "admin.role_menu_relation", autoDelete = "false"),
            exchange = @Exchange(value = "binlog")
        )
    )
    public void listener(String content) {
        // JSONStr 转换为 JSONArray 再转换为 List
        List<Long> ids = JSONUtil.parseArray(content).toList(Long.class);
        // 更新 Redis 中的数据
        resourceService.updateRelationData(ids);
    }
}
