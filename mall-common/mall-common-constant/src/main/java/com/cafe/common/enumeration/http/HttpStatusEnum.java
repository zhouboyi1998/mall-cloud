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
    OFF_SHELVE(601, "Products Off Shelve"),

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
     * 商品概要不存在
     */
    GOODS_SUMMARY_NOT_FOUND(606, "Goods Summary Not Found"),

    /**
     * 商品详情不存在
     */
    GOODS_DETAIL_NOT_FOUND(607, "Goods Detail Not Found"),

    /**
     * 修改商品状态失败
     */
    UPDATE_GOODS_STATUS_FAIL(614, "Update Goods Status Fail"),

    /**
     * 待上架的商品不存在
     */
    PENDING_ON_SHELVE_GOODS_NOT_FOUND(615, "Pending On Shelve Goods Not Found"),

    /**
     * 店铺不存在
     */
    SHOP_NOT_FOUND(616, "Shop Not Found"),

    /**
     * 未知的评论类型
     */
    UNKNOWN_REVIEW_TYPE(618, "Unknown Review Type"),

    /**
     * 商品销量不存在
     */
    GOODS_SALE_NOT_FOUND(619, "Goods Sale Not Found"),

    /**
     * 商品评论数量统计不存在
     */
    GOODS_REVIEW_STATISTIC_NOT_FOUND(620, "Goods Review Statistic Not Found"),

    /**
     * 文本中存在敏感词
     */
    SENSITIVE_WORD_FOUND(622, "Sensitive Word Found"),

    /**
     * ID生成器不存在
     */
    ID_GENERATOR_NOT_FOUND(623, "ID Generator Not Found"),

    /**
     * ID生成器未启用
     */
    ID_GENERATOR_NOT_ENABLED(624, "ID Generator Not Enabled"),

    /**
     * 验证码生成器不存在
     */
    CAPTCHA_GENERATOR_NOT_FOUND(625, "Captcha Generator Not Found"),

    /**
     * 验证码生成器未启用
     */
    CAPTCHA_GENERATOR_NOT_ENABLED(626, "Captcha Generator Not Enabled"),

    ;

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
