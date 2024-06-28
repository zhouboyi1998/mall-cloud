package com.cafe.common.constant.kafka;

import com.cafe.common.constant.mysql.MySQLConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.kafka
 * @Author: zhouboyi
 * @Date: 2023/4/25 15:25
 * @Description: Kafka 相关常量
 */
public class KafkaConstant {

    /**
     * 生产者
     */
    public static class Producer {

        /**
         * debezium 生产者
         */
        public static final String DEBEZIUM = "debezium";
    }

    /**
     * 主题
     */
    public static class Topic {

        /**
         * order 主题
         */
        public static final String ORDER = "order";

        /**
         * order-detail 主题
         */
        public static final String ORDER_DETAIL = "order-detail";

        /**
         * order-vo 主题
         */
        public static final String ORDER_VO = "order-vo";

        /**
         * Producer + TableName 作为组合键, 获取 Topic
         */
        public static final MultiKeyMap<String, String> MAP = new MultiKeyMap<>();

        static {
            MAP.put(Producer.DEBEZIUM, MySQLConstant.DatabaseTable.ORDER, ORDER);
            MAP.put(Producer.DEBEZIUM, MySQLConstant.DatabaseTable.ORDER_DETAIL, ORDER_DETAIL);
        }
    }

    /**
     * 消费者组
     */
    public static class ConsumerGroup {

        /**
         * elasticsearch 消费者组
         */
        public static final String ELASTICSEARCH = "elasticsearch";
    }
}
