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
    public static final String MENU_ROLE_MAP = "AUTH:MENU_ROLE_MAP";

    /**
     * 令牌存储Key前缀
     */
    public static final String TOKEN_PREFIX = "AUTH:TOKEN_";

    /**
     * 验证码Key前缀
     */
    public static final String CAPTCHA_PREFIX = "VALIDATE:CAPTCHA_";
}
