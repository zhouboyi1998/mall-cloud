package com.cafe.common.constant.rocketmq;

import com.cafe.common.constant.mysql.TableConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rocketmq
 * @Author: zhouboyi
 * @Date: 2022/7/29 21:24
 * @Description: RocketMQ 生产者、消息标识(数据库表名称)、RocketMQ 主题对应关系
 */
public class RocketMQTopicMap {

    /**
     * 使用 RocketMQ 生产者、消息标识(数据库表名称) 作为组合Key, 获取 RocketMQ 主题
     */
    public static final MultiKeyMap<String, String> TOPIC_MAP = new MultiKeyMap<>();

    static {
        TOPIC_MAP.put(RocketMQProducer.CANAL, TableConstant.BRAND, RocketMQTopic.CANAL_TO_ES_BRAND);
        TOPIC_MAP.put(RocketMQProducer.CANAL, TableConstant.CATEGORY, RocketMQTopic.CANAL_TO_ES_CATEGORY);
    }
}
