package com.cafe.common.constant.rocketmq;

import com.cafe.common.constant.mysql.TableConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rocketmq
 * @Author: zhouboyi
 * @Date: 2022/7/28 23:56
 * @Description: RocketMQ 主题
 */
public class RocketMQTopic {

    /**
     * Canal 发送消息到 ElasticSearch
     */
    public static final String CANAL_SEND_GOODS_TO_ELASTICSEARCH = "CANAL_SEND_GOODS_TO_ELASTICSEARCH";

    /**
     * Canal 发送消息到 ElasticSearch (品牌数据)
     */
    public static final String CANAL_SEND_BRAND_TO_ELASTICSEARCH = "CANAL_SEND_BRAND_TO_ELASTICSEARCH";

    /**
     * Canal 发送消息到 ElasticSearch (分类数据)
     */
    public static final String CANAL_SEND_CATEGORY_TO_ELASTICSEARCH = "CANAL_SEND_CATEGORY_TO_ELASTICSEARCH";

    /**
     * 使用 RocketMQ Producer、Database Table 作为组合键, 获取 RocketMQ Topic
     */
    public static final MultiKeyMap<String, String> TOPIC_MAP = new MultiKeyMap<>();

    static {
        TOPIC_MAP.put(RocketMQConstant.CANAL, TableConstant.BRAND, CANAL_SEND_BRAND_TO_ELASTICSEARCH);
        TOPIC_MAP.put(RocketMQConstant.CANAL, TableConstant.CATEGORY, CANAL_SEND_CATEGORY_TO_ELASTICSEARCH);
    }
}
