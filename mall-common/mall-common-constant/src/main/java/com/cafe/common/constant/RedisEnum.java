package com.cafe.common.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:09
 * @Description:
 */
public enum RedisEnum {

    RESOURCE_ROLE_MAP("AUTH:RESOURCE_ROLES_MAP"),
    NO_CACHE("no-cache");

    private String value;

    private RedisEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
