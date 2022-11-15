package com.cafe.user.message;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.cafe.user.model.Role;
import com.cafe.user.model.RoleMenu;
import com.cafe.user.service.RoleMenuService;
import com.cafe.user.service.RoleService;
import com.cafe.common.constant.BooleanConstant;
import com.cafe.common.constant.MonitorConstant;
import com.cafe.common.message.rabbitmq.constant.RabbitMQExchange;
import com.cafe.common.message.rabbitmq.constant.RabbitMQQueue;
import com.cafe.common.message.rabbitmq.constant.RabbitMQRoutingKey;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class RabbitMQConsumer {

    private final RoleService roleService;

    private final RoleMenuService roleMenuService;

    @Autowired
    public RabbitMQConsumer(
        RoleService roleService,
        RoleMenuService roleMenuService
    ) {
        this.roleService = roleService;
        this.roleMenuService = roleMenuService;
    }

    /**
     * 监听 RabbitMQ, 接收 RabbitMQQueue.ROLE 消息队列中的消息
     *
     * @param message JSON 字符串格式的消息内容
     */
    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQQueue.ROLE, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitMQExchange.BINLOG),
            key = {RabbitMQRoutingKey.BINLOG_TO_ROLE}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQQueue.ROLE, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitMQExchange.CANAL),
            key = {RabbitMQRoutingKey.CANAL_TO_ROLE}
        ))
    })
    public void listenerRoleQueue(String message) {
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更的类型
        String operation = content.get(MonitorConstant.OPERATION).toString();
        // 获取变更前的数据
        List<Role> beforeDataList = JSONUtil.parseArray(content.get(MonitorConstant.BEFORE_DATA)).toList(Role.class);
        // 获取变更后的数据
        List<Role> afterDataList = JSONUtil.parseArray(content.get(MonitorConstant.AFTER_DATA)).toList(Role.class);

        // 根据变更的类型执行不同的更新 Redis 操作
        if (ObjectUtil.equal(MonitorConstant.UPDATE, operation) || ObjectUtil.equal(MonitorConstant.DELETE, operation)) {
            for (Role role : beforeDataList) {
                roleService.removeRoleNameList(role.getRoleName());
            }
        }
        if (ObjectUtil.equal(MonitorConstant.UPDATE, operation) || ObjectUtil.equal(MonitorConstant.INSERT, operation)) {
            for (Role role : afterDataList) {
                roleService.saveRoleNameList(role.getRoleName());
            }
        }
    }

    /**
     * 监听 RabbitMQ, 接收 RabbitMQQueue.ROLE_MENU 消息队列中的消息
     *
     * @param message JSON 字符串格式的消息内容
     */
    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQQueue.ROLE_MENU, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitMQExchange.BINLOG),
            key = {RabbitMQRoutingKey.BINLOG_TO_ROLE_MENU}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQQueue.ROLE_MENU, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitMQExchange.CANAL),
            key = {RabbitMQRoutingKey.CANAL_TO_ROLE_MENU}
        ))
    })
    public void listenerRoleMenuQueue(String message) {
        // 存储 菜单ids
        List<Long> menuIds = new ArrayList<Long>();
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更前的数据
        List<RoleMenu> beforeDataList = JSONUtil.parseArray(content.get(MonitorConstant.BEFORE_DATA)).toList(RoleMenu.class);
        // 获取变更前的数据
        List<RoleMenu> afterDataList = JSONUtil.parseArray(content.get(MonitorConstant.AFTER_DATA)).toList(RoleMenu.class);

        for (RoleMenu roleMenu : beforeDataList) {
            menuIds.add(roleMenu.getMenuId());
        }
        for (RoleMenu roleMenu : afterDataList) {
            menuIds.add(roleMenu.getMenuId());
        }
        // 更新 Redis 中的数据
        roleMenuService.refreshMenuRoleBO(menuIds);
    }
}
