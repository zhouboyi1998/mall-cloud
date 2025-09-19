/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_storage

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:12:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_stock
-- ----------------------------
DROP TABLE IF EXISTS `mall_stock`;
CREATE TABLE `mall_stock`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '库存ID',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID',
  `shop_id` bigint NOT NULL COMMENT '店铺ID',
  `stock` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '总库存量',
  `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '库存单位',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_stock_item
-- ----------------------------
DROP TABLE IF EXISTS `mall_stock_item`;
CREATE TABLE `mall_stock_item`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '库存明细ID',
  `stock_id` bigint UNSIGNED NOT NULL COMMENT '库存ID',
  `storage_id` bigint UNSIGNED NOT NULL COMMENT '仓库ID',
  `stock` int NOT NULL COMMENT '库存明细的库存量',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_storage
-- ----------------------------
DROP TABLE IF EXISTS `mall_storage`;
CREATE TABLE `mall_storage`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '仓库ID',
  `storage_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `shop_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺ID: 0 平台仓库',
  `province_id` bigint UNSIGNED NOT NULL COMMENT '省份ID',
  `city_id` bigint UNSIGNED NOT NULL COMMENT '城市ID',
  `district_id` bigint UNSIGNED NOT NULL COMMENT '区县ID',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库具体地址',
  `storage_type` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '仓库类型: 1 平台仓库, 2 商家仓库',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库' ROW_FORMAT = DYNAMIC;
