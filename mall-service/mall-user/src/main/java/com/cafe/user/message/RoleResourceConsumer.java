package com.cafe.user.message;

import com.cafe.common.constant.monitor.MonitorConstant;
import com.cafe.common.constant.rabbitmq.RabbitMQConstant;
import com.cafe.common.json.util.JacksonUtil;
import com.cafe.user.facade.RoleResourceFacade;
import com.cafe.user.model.entity.RoleResource;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Argument;
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
 * @Description:
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RoleResourceConsumer {

    private final RoleResourceFacade roleResourceFacade;

    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.BINLOG_ROLE_RESOURCE, durable = Exchange.TRUE, autoDelete = Exchange.FALSE, arguments = {
                @Argument(name = RabbitMQConstant.ArgumentName.X_DEAD_LETTER_EXCHANGE, value = RabbitMQConstant.Exchange.DEAD_LETTER),
                @Argument(name = RabbitMQConstant.ArgumentName.X_DEAD_LETTER_ROUTING_KEY, value = RabbitMQConstant.RoutingKey.BINLOG_ROLE_RESOURCE)
            }),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.BINLOG),
            key = {RabbitMQConstant.RoutingKey.BINLOG_ROLE_RESOURCE}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.CANAL_ROLE_RESOURCE, durable = Exchange.TRUE, autoDelete = Exchange.FALSE, arguments = {
                @Argument(name = RabbitMQConstant.ArgumentName.X_DEAD_LETTER_EXCHANGE, value = RabbitMQConstant.Exchange.DEAD_LETTER),
                @Argument(name = RabbitMQConstant.ArgumentName.X_DEAD_LETTER_ROUTING_KEY, value = RabbitMQConstant.RoutingKey.CANAL_ROLE_RESOURCE)
            }),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.CANAL),
            key = {RabbitMQConstant.RoutingKey.CANAL_ROLE_RESOURCE}
        ))
    })
    public void refreshResourceRoleListener(String message) {
        // 打印成功接收消息的日志
        log.info("RoleResourceConsumer.refreshResourceRoleListener(): rabbitmq message -> {}", message);

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

    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.BINLOG_ROLE_RESOURCE_DEAD_LETTER, durable = Exchange.TRUE, autoDelete = Exchange.FALSE),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.DEAD_LETTER),
            key = {RabbitMQConstant.RoutingKey.BINLOG_ROLE_RESOURCE}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.CANAL_ROLE_RESOURCE_DEAD_LETTER, durable = Exchange.TRUE, autoDelete = Exchange.FALSE),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.DEAD_LETTER),
            key = {RabbitMQConstant.RoutingKey.CANAL_ROLE_RESOURCE}
        ))
    })
    public void deadLetterListener(String message) {
        log.error("RoleResourceConsumer.deadLetterListener(): rabbitmq message -> {}", message);
    }
}
