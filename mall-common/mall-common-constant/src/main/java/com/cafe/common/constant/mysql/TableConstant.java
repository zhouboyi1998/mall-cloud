package com.cafe.common.constant.mysql;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.mysql
 * @Author: zhouboyi
 * @Date: 2022/5/6 14:42
 * @Description: 数据库表相关常量
 */
public class TableConstant {

    /**
     * 用户数据库.角色表
     */
    public static final String ROLE = "mall_user.mall_role";

    /**
     * 用户数据库.角色-菜单关联关系表
     */
    public static final String ROLE_MENU = "mall_user.mall_role_menu";

    /**
     * 商品数据库.品牌表
     */
    public static final String BRAND = "mall_goods.mall_brand";

    /**
     * 商品数据库.分类表
     */
    public static final String CATEGORY = "mall_goods.mall_category";

    /**
     * 订单数据库.订单表
     */
    public static final String ORDER = "mall_order.mall_order";

    /**
     * 订单数据库.订单详情表
     */
    public static final String ORDER_DETAIL = "mall_order.mall_order_detail";
}
