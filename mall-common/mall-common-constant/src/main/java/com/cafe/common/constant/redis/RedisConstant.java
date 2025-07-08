package com.cafe.common.constant.redis;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.redis
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:09
 * @Description: Redis 相关常量
 */
public class RedisConstant {

    /**
     * 资源-角色对应关系Key
     */
    public static final String RESOURCE_ROLE_MAP = "AUTH:RESOURCE_ROLE_MAP";

    /**
     * 令牌存储Key前缀
     */
    public static final String TOKEN_PREFIX = "AUTH:TOKEN_";

    /**
     * 访问令牌Key中缀
     */
    public static final String ACCESS_TOKEN_INFIX = "access:";

    /**
     * 验证码Key前缀
     */
    public static final String CAPTCHA_PREFIX = "VALIDATE:CAPTCHA_";

    /**
     * 异常应急返回结果Key前缀
     */
    public static final String FALLBACK_PREFIX = "CACHE:FALLBACK_";

    /**
     * 幂等性Key前缀
     */
    public static final String IDEMPOTENCE_PREFIX = "LIMIT:IDEMPOTENCE_";

    /**
     * 分布式ID Key前缀
     */
    public static final String ID_PREFIX = "DISTRIBUTED:ID_";

    /**
     * 默认的分布式ID分组
     */
    public static final String DEFAULT_ID_GROUP = "default";
}
