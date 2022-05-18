package com.cafe.common.enumeration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration
 * @Author: zhouboyi
 * @Date: 2022/5/18 18:25
 * @Description: RabbitMQ 交换机枚举
 */
public enum RabbitmqExchangeEnum {

    /**
     * 枚举值
     */
    BINLOG("binlog");

    /**
     * 交换机名称
     */
    private String name;

    RabbitmqExchangeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
