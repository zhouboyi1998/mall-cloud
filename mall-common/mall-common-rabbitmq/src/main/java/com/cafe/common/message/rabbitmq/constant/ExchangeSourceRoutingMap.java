package com.cafe.common.message.rabbitmq.constant;

import com.cafe.admin.constant.AdminTable;
import com.cafe.common.constant.RabbitmqExchange;
import com.cafe.common.constant.RabbitmqRoutingKey;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.message.rabbitmq.constant
 * @Author: zhouboyi
 * @Date: 2022/5/20 11:06
 * @Description: 交换机、消息标识、路由键对应关系, 通过前两者获取后者
 */
public class ExchangeSourceRoutingMap {

    public static final MultiKeyMap<String, String> EXCHANGE_SOURCE_ROUTING_MAP = new MultiKeyMap<String, String>() {{
        put(RabbitmqExchange.BINLOG, AdminTable.ROLE, RabbitmqRoutingKey.BINLOG_TO_ROLE);
        put(RabbitmqExchange.BINLOG, AdminTable.ROLE_MENU, RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU);
    }};
}
