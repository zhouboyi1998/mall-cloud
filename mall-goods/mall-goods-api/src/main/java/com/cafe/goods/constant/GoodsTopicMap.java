package com.cafe.goods.constant;

import com.cafe.common.message.rocketmq.constant.RocketMQProducer;
import com.cafe.common.message.rocketmq.constant.RocketMQTopic;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.constant
 * @Author: zhouboyi
 * @Date: 2022/7/29 21:24
 * @Description:
 */
public class GoodsTopicMap {

    public static final MultiKeyMap<String, String> TOPIC_MAP = new MultiKeyMap<String, String>() {{
        put(RocketMQProducer.CANAL, GoodsTable.BRAND, RocketMQTopic.CANAL_TO_ES_BRAND);
        put(RocketMQProducer.CANAL, GoodsTable.CATEGORY, RocketMQTopic.CANAL_TO_ES_CATEGORY);
    }};
}
