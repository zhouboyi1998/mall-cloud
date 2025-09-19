/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_merchant

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:12:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_merchant
-- ----------------------------
DROP TABLE IF EXISTS `mall_merchant`;
CREATE TABLE `mall_merchant`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '商家ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家昵称',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家头像',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商家' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_merchant_shop
-- ----------------------------
DROP TABLE IF EXISTS `mall_merchant_shop`;
CREATE TABLE `mall_merchant_shop`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '商家-店铺关联关系ID',
  `merchant_id` bigint UNSIGNED NOT NULL COMMENT '商家ID',
  `shop_id` bigint UNSIGNED NOT NULL COMMENT '店铺ID',
  `position` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '职位: 0 店员, 1 店长, 2 店主',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商家-店铺关联关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_shop
-- ----------------------------
DROP TABLE IF EXISTS `mall_shop`;
CREATE TABLE `mall_shop`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '店铺ID',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺名称',
  `shop_type` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺类型: 0 自营店铺, 1 第三方店铺',
  `logo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '店铺Logo',
  `intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '店铺简介',
  `telephone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '店铺客服电话',
  `business_license` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '营业执照',
  `audit` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核: 0 未审核, 1 驳回, 2 通过',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 停业, 1 营业',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '店铺' ROW_FORMAT = DYNAMIC;
