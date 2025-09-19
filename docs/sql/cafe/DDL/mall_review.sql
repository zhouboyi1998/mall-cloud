/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_review

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:12:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_goods_review
-- ----------------------------
DROP TABLE IF EXISTS `mall_goods_review`;
CREATE TABLE `mall_goods_review`  (
  `id` bigint NOT NULL COMMENT '商品-评论关联关系ID',
  `order_item_id` bigint NOT NULL COMMENT '订单明细ID',
  `spu_id` bigint NOT NULL COMMENT 'SPU ID',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID',
  `review_id` bigint NOT NULL COMMENT '评论ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品-评论关联关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_order_review
-- ----------------------------
DROP TABLE IF EXISTS `mall_order_review`;
CREATE TABLE `mall_order_review`  (
  `id` bigint NOT NULL COMMENT '订单-评论关联关系ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `review_id` bigint NOT NULL COMMENT '评论ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单-评论关联关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_review
-- ----------------------------
DROP TABLE IF EXISTS `mall_review`;
CREATE TABLE `mall_review`  (
  `id` bigint NOT NULL COMMENT '评论ID',
  `rating` int NOT NULL COMMENT '评分 (1-5分)',
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文字评论',
  `audit` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核: 0 未审核, 1 驳回, 2 通过, 3 精选',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 草稿, 1 发布',
  `review_type` int UNSIGNED NOT NULL COMMENT '评论类型: 1 仅评分, 2 有标签, 3 文字评论',
  `target_type` int UNSIGNED NOT NULL COMMENT '目标类型: 1 商品评论, 2 订单评论',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_review_tag
-- ----------------------------
DROP TABLE IF EXISTS `mall_review_tag`;
CREATE TABLE `mall_review_tag`  (
  `id` bigint NOT NULL COMMENT '评论-标签关联关系ID',
  `review_id` bigint NOT NULL COMMENT '评论ID',
  `tag_id` bigint NOT NULL COMMENT '标签ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论-标签关联关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_tag
-- ----------------------------
DROP TABLE IF EXISTS `mall_tag`;
CREATE TABLE `mall_tag`  (
  `id` bigint NOT NULL COMMENT '标签ID',
  `tag_type` int UNSIGNED NOT NULL COMMENT '标签类型: 1 快递包装, 2 送货速度, 3 配送员服务',
  `tag_content` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签内容',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0 停用, 1 启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
