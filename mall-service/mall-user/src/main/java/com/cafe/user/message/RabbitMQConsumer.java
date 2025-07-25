package com.cafe.user.message;

import com.cafe.common.constant.monitor.MonitorConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.rabbitmq.RabbitMQConstant;
import com.cafe.common.json.util.JacksonUtil;
import com.cafe.user.facade.RoleResourceFacade;
import com.cafe.user.model.entity.RoleResource;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.message
 * @Author: zhouboyi
 * @Date: 2022/5/18 14:56
 * @Description: RabbitMQ 消息消费者 (接收数据库表修改消息)
 */
@RequiredArgsConstructor
@Component
public class RabbitMQConsumer {

    private final RoleResourceFacade roleResourceFacade;

    /**
     * 监听 RabbitMQ, 接收角色-资源队列中的消息
     *
     * @param message JSON 字符串格式的消息内容
     */
    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.ROLE_RESOURCE, durable = StringConstant.TRUE, autoDelete = StringConstant.FALSE),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.BINLOG),
            key = {RabbitMQConstant.RoutingKey.BINLOG_TO_ROLE_RESOURCE}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.ROLE_RESOURCE, durable = StringConstant.TRUE, autoDelete = StringConstant.FALSE),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.CANAL),
            key = {RabbitMQConstant.RoutingKey.CANAL_TO_ROLE_RESOURCE}
        ))
    })
    public void listenerRoleResourceQueue(String message) {
        // 存储资源ID列表
        List<Long> resourceIds = new ArrayList<>();
        // 获取消息内容
        Map<String, Object> content = JacksonUtil.readValue(message, new TypeReference<Map<String, Object>>() {});
        // 获取变更前的数据
        List<RoleResource> beforeDataList = JacksonUtil.convertValue(content.get(MonitorConstant.BEFORE_DATA), new TypeReference<List<RoleResource>>() {});
        // 获取变更前的数据
        List<RoleResource> afterDataList = JacksonUtil.convertValue(content.get(MonitorConstant.AFTER_DATA), new TypeReference<List<RoleResource>>() {});

        for (RoleResource roleResource : beforeDataList) {
            resourceIds.add(roleResource.getResourceId());
        }
        for (RoleResource roleResource : afterDataList) {
            resourceIds.add(roleResource.getResourceId());
        }
        // 更新 Redis 中的数据
        roleResourceFacade.refreshResourceRoleCache(resourceIds);
    }
}
