package com.cafe.admin.message;

import cn.hutool.json.JSONUtil;
import com.cafe.admin.model.Role;
import com.cafe.admin.model.RoleMenu;
import com.cafe.admin.service.RoleMenuService;
import com.cafe.admin.service.RoleService;
import com.cafe.common.constant.*;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更的类型
        String operation = content.get(MonitorConstant.OPERATION).toString();
        // 获取变更的数据, 类型为 JSONString, 先解析成 JSONArray, 再转换为 List
        List<Role> roleList = JSONUtil.parseArray(content.get(MonitorConstant.DATA)).toList(Role.class);

        // 根据变更的类型执行不同的 Redis 操作
        switch (operation) {
            case MonitorConstant.INSERT:
            case MonitorConstant.UPDATE_AFTER:
                for (Role role : roleList) {
                    roleService.saveRoleNameMap(role.getRoleName());
                }
                break;
            case MonitorConstant.DELETE:
            case MonitorConstant.UPDATE_BEFORE:
                for (Role role : roleList) {
                    roleService.removeRoleNameMap(role.getRoleName());
                }
                break;
            default:
                break;
        }
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
        // 获取消息内容
        Map<String, Object> content = JSONUtil.parseObj(message);
        // 获取变更的数据, 类型为 JSONString, 先解析成 JSONArray, 再转换为 List
        List<RoleMenu> roleMenuList = JSONUtil.parseArray(content.get(MonitorConstant.DATA)).toList(RoleMenu.class);
        for (RoleMenu roleMenu : roleMenuList) {
            menuIds.add(roleMenu.getMenuId());
        }
        // 更新 Redis 中的数据
        roleMenuService.refreshMenuRoleRelationBO(menuIds);
    }
}
