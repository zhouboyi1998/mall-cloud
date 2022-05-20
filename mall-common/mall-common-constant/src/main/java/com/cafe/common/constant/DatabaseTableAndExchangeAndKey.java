package com.cafe.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/5/20 11:06
 * @Description: 数据库表、交换机、路由键对应关系
 */
public class DatabaseTableAndExchangeAndKey {

    public static final Map<String, HashMap<String, String>> TABLE_EXCHANGE_KEY_MAP = new HashMap<String, HashMap<String, String>>() {{
        put(DatabaseTable.ROLE_MENU_RELATION, new HashMap<String, String>() {{
            put(RabbitmqExchange.BINLOG, RabbitmqRoutingKey.BINLOG_TO_ROLE_MENU_RELATION);
        }});
    }};
}
