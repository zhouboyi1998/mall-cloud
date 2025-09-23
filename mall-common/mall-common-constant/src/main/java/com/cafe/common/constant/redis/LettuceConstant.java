package com.cafe.common.constant.redis;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.redis
 * @Author: zhouboyi
 * @Date: 2025/9/23 15:50
 * @Description: Lettuce 相关常量
 */
public class LettuceConstant {

    /**
     * 分布式ID Key前缀
     */
    public static final String ID_PREFIX = "LETTUCE_DISTRIBUTED:ID_";

    /**
     * 默认的分布式ID分组
     */
    public static final String DEFAULT_ID_GROUP = "default";
}
