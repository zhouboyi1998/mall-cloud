package com.cafe.common.constant.rocketmq;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.rocketmq
 * @Author: zhouboyi
 * @Date: 2022/7/28 23:56
 * @Description: RocketMQ 主题名称
 */
public class RocketMQTopic {

    /**
     * Canal 模块发送商品消息到 ElasticSearch 的主题
     */
    public static final String CANAL_TO_ES_GOODS = "CANAL-TO-ES-GOODS";

    /**
     * Canal 模块发送品牌消息到 ElasticSearch 的主题
     */
    public static final String CANAL_TO_ES_BRAND = "CANAL-TO-ES-BRAND";

    /**
     * Canal 模块发送分类消息到 ElasticSearch 的主题
     */
    public static final String CANAL_TO_ES_CATEGORY = "CANAL-TO-ES-CATEGORY";
}
