package com.cafe.security.manage.message;

import cn.hutool.json.JSONUtil;
import com.cafe.admin.model.RoleMenu;
import com.cafe.common.constant.RabbitmqExchange;
import com.cafe.common.constant.RabbitmqQueue;
import com.cafe.common.constant.RabbitmqRoutingKey;
import com.cafe.common.constant.BooleanConstant;
import com.cafe.security.manage.service.ResourceService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.message
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
    public void listener(String message) {
        // 存储 菜单ids
        List<Long> menuIds = new ArrayList<Long>();
        // JSONStr 转换为 JSONArray 再转换为 List
        List<RoleMenu> roleMenuList = JSONUtil.parseArray(message).toList(RoleMenu.class);
        for (RoleMenu roleMenu : roleMenuList) {
            menuIds.add(roleMenu.getMenuId());
        }
        // 更新 Redis 中的数据
        resourceService.updateRelationData(menuIds);
    }
}
