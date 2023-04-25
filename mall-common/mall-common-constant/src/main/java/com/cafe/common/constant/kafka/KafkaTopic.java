package com.cafe.common.constant.kafka;

import com.cafe.common.constant.mysql.TableConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.kafka
 * @Author: zhouboyi
 * @Date: 2023/4/25 15:25
 * @Description: Kafka 主题
 */
public class KafkaTopic {

    /**
     * Debezium 发送到 ElasticSearch (订单数据)
     */
    public static final String DEBEZIUM_SEND_ORDER_TO_ELASTICSEARCH = "DEBEZIUM_SEND_ORDER_TO_ELASTICSEARCH";

    /**
     * Debezium 发送到 ElasticSearch (订单详情数据)
     */
    public static final String DEBEZIUM_SEND_ORDER_DETAIL_TO_ELASTICSEARCH = "DEBEZIUM_SEND_ORDER_DETAIL_TO_ELASTICSEARCH";

    /**
     * 使用 Kafka Producer、Database Table 作为组合键, 获取 Kafka Topic
     */
    public static final MultiKeyMap<String, String> TOPIC_MAP = new MultiKeyMap<>();

    static {
        TOPIC_MAP.put(KafkaConstant.DEBEZIUM, TableConstant.ORDER, DEBEZIUM_SEND_ORDER_TO_ELASTICSEARCH);
        TOPIC_MAP.put(KafkaConstant.DEBEZIUM, TableConstant.ORDER_DETAIL, DEBEZIUM_SEND_ORDER_DETAIL_TO_ELASTICSEARCH);
    }
}
