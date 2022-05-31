package com.cafe.common.monitor.message.constant;

import com.cafe.admin.constant.AdminTable;
import com.cafe.common.constant.RabbitmqExchange;
import com.cafe.common.constant.RabbitmqRoutingKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.monitor.message.constant
 * @Author: zhouboyi
 * @Date: 2022/5/20 11:06
 * @Description: 交换机、消息标识、路由键对应关系, 通过前两者获取后者
 */
public class ExchangeRoutingKeyMap {

    public static final Map<String, String> EXCHANGE_SOURCE_ROUTING_MAP = new HashMap<String, String>() {{
        put(RabbitmqExchange.BINLOG + "::" + AdminTable.ROLE_MENU_RELATION, RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU_RELATION);
    }};

    public static String getRoutingKey(String exchange, String source) {
        return EXCHANGE_SOURCE_ROUTING_MAP.get(exchange + "::" + source);
    }
}
