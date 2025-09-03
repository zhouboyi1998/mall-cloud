package com.cafe.common.enumeration.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.http
 * @Author: zhouboyi
 * @Date: 2022/5/9 9:09
 * @Description: 自定义状态码枚举
 */
@Getter
@AllArgsConstructor
public enum HttpStatusEnum {

    /**
     * 成功
     */
    SUCCESS(200, "Success"),

    /**
     * 用户名不存在
     */
    USERNAME_NOT_FOUND(401, "Username Not Found"),

    /**
     * 手机号不存在
     */
    MOBILE_NOT_FOUND(401, "Mobile Not Found"),

    /**
     * 邮箱不存在
     */
    EMAIL_NOT_FOUND(401, "Email Not Found"),

    /**
     * 角色未分配
     */
    ROLE_UNASSIGNED(401, "Role Unassigned"),

    /**
     * 账号已禁用
     */
    ACCOUNT_DISABLED(401, "Account Disabled"),

    /**
     * 账号被锁定
     */
    ACCOUNT_LOCKED(401, "Account Locked"),

    /**
     * 账号已过期
     */
    ACCOUNT_EXPIRED(401, "Account Expired"),

    /**
     * 证书已过期
     */
    CREDENTIALS_EXPIRED(401, "Credentials Expired"),

    /**
     * 失败
     */
    FAIL(500, "Fail"),

    /**
     * 商品已下架
     */
    UNLISTED(601, "Products Unlisted"),

    /**
     * 收货地址不存在
     */
    ADDRESS_NOT_FOUND(602, "Address Not Found"),

    /**
     * 区域不存在
     */
    AREA_NOT_FOUND(603, "Area Not Found"),

    /**
     * 库存不足
     */
    LOW_STOCK(604, "Low Stock"),

    /**
     * 创建订单异常
     */
    CREATE_ORDER_EXCEPTION(605, "Create Order Exception"),

    /**
     * 没有匹配的商品
     */
    NO_MATCH_GOODS(701, "No Match Goods"),

    /**
     * 商品概要不存在
     */
    GOODS_SUMMARY_NOT_FOUND(702, "Goods Summary Not Found"),

    /**
     * SPU 不存在
     */
    SPU_NOT_FOUND(703, "Spu Not Found");

    /**
     * 状态码
     */
    private final Integer value;

    /**
     * 状态信息
     */
    private final String reasonPhrase;

    public Integer value() {
        return value;
    }
}
