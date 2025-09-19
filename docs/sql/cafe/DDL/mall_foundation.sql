/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_foundation

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:11:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_area
-- ----------------------------
DROP TABLE IF EXISTS `mall_area`;
CREATE TABLE `mall_area`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '区域ID',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级区域ID',
  `area_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '区域名称',
  `area_code` int NOT NULL COMMENT '行政区划编码',
  `post_code` int NULL DEFAULT NULL COMMENT '邮政编码',
  `level` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '区域等级: 1 省份, 2 城市, 3 区县',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '区域' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_interference
-- ----------------------------
DROP TABLE IF EXISTS `mall_interference`;
CREATE TABLE `mall_interference`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '干扰符ID',
  `interference_symbol` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '干扰符',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '干扰符' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mall_sensitive
-- ----------------------------
DROP TABLE IF EXISTS `mall_sensitive`;
CREATE TABLE `mall_sensitive`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '敏感词ID',
  `sensitive_word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '敏感词',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '敏感词' ROW_FORMAT = Dynamic;
