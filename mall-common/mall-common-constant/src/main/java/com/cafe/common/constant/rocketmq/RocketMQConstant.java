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
         * goods-index 主题
         */
        public static final String GOODS_INDEX = "goods-index";

        /**
         * brand 主题
         */
        public static final String BRAND = "brand";

        /**
         * category 主题
         */
        public static final String CATEGORY = "category";

        /**
         * sensitive 主题
         */
        public static final String SENSITIVE = "sensitive";

        /**
         * Producer + TableName 作为组合键, 获取 Topic
         */
        public static final MultiKeyMap<String, String> MAP = new MultiKeyMap<>();

        static {
            MAP.put(Producer.CANAL, MySQLConstant.DatabaseTable.BRAND, BRAND);
            MAP.put(Producer.CANAL, MySQLConstant.DatabaseTable.CATEGORY, CATEGORY);
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

        /**
         * foundation 消费者组
         */
        public static final String FOUNDATION = "foundation";
    }

    /**
     * 哈希键
     */
    public static class HashKey {

        /**
         * 敏感词消息通用哈希键
         */
        public static final String SENSITIVE = "sensitive";
    }
}
