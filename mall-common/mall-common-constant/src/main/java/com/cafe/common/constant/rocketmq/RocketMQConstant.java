package com.cafe.common.constant.rocketmq;

import com.cafe.common.constant.mysql.MySQLConstant;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rocketmq
 * @Author: zhouboyi
 * @Date: 2023/4/11 23:48
 * @Description: RocketMQ 相关常量
 */
public class RocketMQConstant {

    /**
     * 日志系统参数
     */
    public static final String ROCKETMQ_CLIENT_LOG = "rocketmq.client.logUseSlf4j";

    /**
     * 生产者
     */
    public static class Producer {

        /**
         * canal 生产者
         */
        public static final String CANAL = "canal";
    }

    /**
     * 主题
     */
    public static class Topic {

        /**
         * canal 生产者 --> goods 主题
         */
        public static final String CANAL_TO_GOODS = "canal-to-goods";

        /**
         * canal 生产者 --> brand 主题
         */
        public static final String CANAL_TO_BRAND = "canal-to-brand";

        /**
         * canal 生产者 --> category 主题
         */
        public static final String CANAL_TO_CATEGORY = "canal-to-category";

        /**
         * Producer + TableName 作为组合键, 获取 Topic
         */
        public static final MultiKeyMap<String, String> MAP = new MultiKeyMap<>();

        static {
            MAP.put(Producer.CANAL, MySQLConstant.DatabaseTable.BRAND, CANAL_TO_BRAND);
            MAP.put(Producer.CANAL, MySQLConstant.DatabaseTable.CATEGORY, CANAL_TO_CATEGORY);
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
