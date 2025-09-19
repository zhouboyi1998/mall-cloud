/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_order

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:12:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_aftersale
-- ----------------------------
DROP TABLE IF EXISTS `mall_aftersale`;
CREATE TABLE `mall_aftersale`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '售后服务ID',
  `member_id` bigint UNSIGNED NOT NULL COMMENT '会员ID',
  `order_item_id` bigint UNSIGNED NOT NULL COMMENT '订单明细ID',
  `aftersale_type` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '售后类型: 0 退货, 1 换货, 2 保障',
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '售后原因',
  `image_list` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片列表',
  `refund_amount` decimal(16, 2) NOT NULL COMMENT '退款金额',
  `refund_channel` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款渠道: 0 银行卡, 1 支付宝, 2 微信',
  `audit` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核: 0 未审核, 1 驳回, 2 通过',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 申请中, 1 售后中, 2 中止, 3 完成',
  `create_time` datetime NOT NULL COMMENT '申请时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `completion_time` datetime NULL DEFAULT NULL COMMENT '售后完成时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '售后' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_guarantee
-- ----------------------------
DROP TABLE IF EXISTS `mall_guarantee`;
CREATE TABLE `mall_guarantee`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '保障ID',
  `order_item_id` bigint NOT NULL COMMENT '订单明细ID',
  `guarantee_type` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '保障类型: 0 无理由退款, 1 保修, 2 以旧换新',
  `guarantee_period` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '保障期限 (单位: 天)',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 失效, 1 生效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `effect_time` datetime NULL DEFAULT NULL COMMENT '生效时间',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '保障' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_order
-- ----------------------------
DROP TABLE IF EXISTS `mall_order`;
CREATE TABLE `mall_order`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '订单ID',
  `order_no` bigint UNSIGNED NOT NULL COMMENT '订单编号',
  `member_id` bigint UNSIGNED NOT NULL COMMENT '会员ID',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货地址快照',
  `receiver` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人快照',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人手机号快照',
  `amount` decimal(16, 2) NOT NULL COMMENT '商品总金额',
  `discount` decimal(16, 2) NOT NULL DEFAULT 0.00 COMMENT '折扣/满减金额',
  `coupon` decimal(16, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠券金额',
  `postage` decimal(16, 2) NOT NULL DEFAULT 0.00 COMMENT '邮费',
  `payment` decimal(16, 2) NOT NULL COMMENT '实际支付金额',
  `channel` int UNSIGNED NULL DEFAULT 0 COMMENT '支付渠道: 0 银行卡, 1 支付宝, 2 微信',
  `invoice` int UNSIGNED NULL DEFAULT 0 COMMENT '发票: 0 不开发票, 1 电子发票',
  `review` int NOT NULL DEFAULT 0 COMMENT '评价: 0 未评价, 1 已评价',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 下单, 1 支付中, 2 付款失败, 3 付款成功, 4 申请退款, 5 退款成功, 6 已发货, 7 完成, 8 取消',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `deliver_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
  `completion_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_order_item
-- ----------------------------
DROP TABLE IF EXISTS `mall_order_item`;
CREATE TABLE `mall_order_item`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '订单明细ID',
  `order_id` bigint UNSIGNED NOT NULL COMMENT '订单ID',
  `sku_id` bigint UNSIGNED NOT NULL COMMENT 'SKU ID',
  `shop_id` bigint UNSIGNED NOT NULL COMMENT '店铺ID',
  `storage_id` bigint NULL DEFAULT NULL COMMENT '仓库ID',
  `sku_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU 名称快照',
  `sku_image` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT 'SKU 主图快照',
  `sku_price` decimal(16, 2) NOT NULL COMMENT 'SKU 价格快照',
  `sku_quantity` int UNSIGNED NOT NULL COMMENT 'SKU 购买数量',
  `amount` decimal(16, 2) NOT NULL COMMENT '订单明细的实际支付金额',
  `review` int NOT NULL DEFAULT 0 COMMENT '评价: 0 未评价, 1 已评价',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 未完成, 1 已完成',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `deliver_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
  `completion_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单明细' ROW_FORMAT = DYNAMIC;
