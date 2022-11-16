package com.cafe.common.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:09
 * @Description: Redis 相关常量
 */
public class RedisConstant {

    /**
     * 资源-角色对应关系的 Key
     */
    public static final String MENU_ROLE_MAP = "AUTH:MENU_ROLE_MAP";

    /**
     * 角色名称列表
     */
    public static final String ROLE_NAME_LIST = "AUTH:ROLE_NAME_LIST";

    /**
     * 令牌存储相关的 Key 前缀
     */
    public static final String TOKEN_PREFIX = "AUTH:TOKEN_";
}
