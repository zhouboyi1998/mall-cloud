/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_goods

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:12:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_brand
-- ----------------------------
DROP TABLE IF EXISTS `mall_brand`;
CREATE TABLE `mall_brand`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '品牌ID',
  `brand_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '品牌名称',
  `letter` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '首字母',
  `brand_image` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌图片',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '品牌' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_category
-- ----------------------------
DROP TABLE IF EXISTS `mall_category`;
CREATE TABLE `mall_category`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '分类ID',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级分类ID',
  `category_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `level` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '分类等级: 1 一级分类, 2 二级分类, 3 三级分类',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_category_brand
-- ----------------------------
DROP TABLE IF EXISTS `mall_category_brand`;
CREATE TABLE `mall_category_brand`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '分类-品牌关联关系ID',
  `category_id` bigint UNSIGNED NOT NULL COMMENT '分类ID',
  `brand_id` bigint UNSIGNED NOT NULL COMMENT '品牌ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类-品牌关联关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_sku
-- ----------------------------
DROP TABLE IF EXISTS `mall_sku`;
CREATE TABLE `mall_sku`  (
  `id` bigint UNSIGNED NOT NULL COMMENT 'SKU ID',
  `sku_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU 名称',
  `spu_id` bigint UNSIGNED NOT NULL COMMENT 'SPU ID',
  `original_price` decimal(16, 2) NOT NULL DEFAULT 0.00 COMMENT 'SKU 原价',
  `discount_price` decimal(16, 2) NULL DEFAULT NULL COMMENT 'SKU 折扣价',
  `seckill_price` decimal(16, 2) NULL DEFAULT NULL COMMENT 'SKU 秒杀价',
  `specification` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SKU 规格',
  `image` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SKU 主图',
  `image_list` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SKU 图片列表',
  `video` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SKU 视频',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `audit` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核: 0 未审核, 1 驳回, 2 通过',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 下架, 1 上架',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `launch_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存量单位' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_spu
-- ----------------------------
DROP TABLE IF EXISTS `mall_spu`;
CREATE TABLE `mall_spu`  (
  `id` bigint UNSIGNED NOT NULL COMMENT 'SPU ID',
  `spu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'SPU 名称',
  `brand_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '品牌ID',
  `category_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '分类ID',
  `shop_id` bigint NOT NULL COMMENT '店铺ID',
  `caption` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT 'SPU 说明',
  `intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'SPU 详细介绍',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `audit` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核: 0 未审核, 1 驳回, 2 通过',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 下架, 1 上架',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `launch_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标准化产品单元' ROW_FORMAT = DYNAMIC;
