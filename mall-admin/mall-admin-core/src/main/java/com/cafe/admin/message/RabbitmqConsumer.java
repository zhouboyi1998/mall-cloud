package com.cafe.admin.message;

import cn.hutool.json.JSONUtil;
import com.cafe.admin.model.Role;
import com.cafe.admin.model.RoleMenu;
import com.cafe.admin.service.RoleMenuService;
import com.cafe.admin.service.RoleService;
import com.cafe.common.constant.BooleanConstant;
import com.cafe.common.constant.RabbitmqExchange;
import com.cafe.common.constant.RabbitmqQueue;
import com.cafe.common.constant.RabbitmqRoutingKey;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.message
 * @Author: zhouboyi
 * @Date: 2022/5/18 14:56
 * @Description: RabbitMQ 消息消费者 (接收数据库表修改消息)
 */
@Component
public class RabbitmqConsumer {

    private RoleService roleService;

    private RoleMenuService roleMenuService;

    @Autowired
    public RabbitmqConsumer(
        RoleService roleService,
        RoleMenuService roleMenuService
    ) {
        this.roleService = roleService;
        this.roleMenuService = roleMenuService;
    }

    /**
     * 监听 RabbitMQ, 接收 role 队列中的消息
     *
     * @param message JSON 字符串格式的消息内容
     */
    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqQueue.ROLE, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitmqExchange.BINLOG),
            key = {RabbitmqRoutingKey.BINLOG_TO_ROLE}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqQueue.ROLE, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitmqExchange.CANAL),
            key = {RabbitmqRoutingKey.CANAL_TO_ROLE}
        ))
    })
    public void listenerRoleQueue(String message) {
        // 只要接收到更新的消息, 直接调用 init 方法重新保存 RoleNameList
        roleService.initRoleNameMap();
    }

    /**
     * 监听 RabbitMQ, 接收 role-menu 队列中的消息
     *
     * @param message JSON 字符串格式的消息内容
     */
    @RabbitListeners(value = {
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqQueue.ROLE_MENU, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitmqExchange.BINLOG),
            key = {RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU}
        )),
        @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqQueue.ROLE_MENU, durable = BooleanConstant.TRUE, autoDelete = BooleanConstant.FALSE),
            exchange = @Exchange(value = RabbitmqExchange.CANAL),
            key = {RabbitmqRoutingKey.CANAL_TO_ROLE_MENU}
        ))
    })
    public void listenerRoleMenuQueue(String message) {
        // 存储 菜单ids
        List<Long> menuIds = new ArrayList<Long>();
        // JSONStr 转换为 JSONArray 再转换为 List
        List<RoleMenu> roleMenuList = JSONUtil.parseArray(message).toList(RoleMenu.class);
        for (RoleMenu roleMenu : roleMenuList) {
            menuIds.add(roleMenu.getMenuId());
        }
        // 更新 Redis 中的数据
        roleMenuService.refreshMenuRoleRelationBO(menuIds);
    }
}
