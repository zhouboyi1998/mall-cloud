package com.cafe.user.message;

import com.cafe.common.constant.monitor.MonitorConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.rabbitmq.RabbitMQConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.user.model.entity.RoleMenu;
import com.cafe.user.service.RoleMenuService;
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

    private final RoleMenuService roleMenuService;

    /**
     * 监听 RabbitMQ, 接收角色-菜单队列中的消息
     *
     * @param message JSON 字符串格式的消息内容
     */
    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.ROLE_MENU, durable = StringConstant.TRUE, autoDelete = StringConstant.FALSE),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.BINLOG),
            key = {RabbitMQConstant.RoutingKey.BINLOG_TO_ROLE_MENU}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.Queue.ROLE_MENU, durable = StringConstant.TRUE, autoDelete = StringConstant.FALSE),
            exchange = @Exchange(value = RabbitMQConstant.Exchange.CANAL),
            key = {RabbitMQConstant.RoutingKey.CANAL_TO_ROLE_MENU}
        ))
    })
    public void listenerRoleMenuQueue(String message) {
        // 存储 菜单ids
        List<Long> menuIds = new ArrayList<>();
        // 获取消息内容
        Map<String, Object> content = JacksonUtil.readValue(message, new TypeReference<Map<String, Object>>() {});
        // 获取变更前的数据
        List<RoleMenu> beforeDataList = JacksonUtil.convertValue(content.get(MonitorConstant.BEFORE_DATA), new TypeReference<List<RoleMenu>>() {});
        // 获取变更前的数据
        List<RoleMenu> afterDataList = JacksonUtil.convertValue(content.get(MonitorConstant.AFTER_DATA), new TypeReference<List<RoleMenu>>() {});

        for (RoleMenu roleMenu : beforeDataList) {
            menuIds.add(roleMenu.getMenuId());
        }
        for (RoleMenu roleMenu : afterDataList) {
            menuIds.add(roleMenu.getMenuId());
        }
        // 更新 Redis 中的数据
        roleMenuService.refreshMenuRoleCache(menuIds);
    }
}
