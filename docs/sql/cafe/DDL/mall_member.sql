/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_member

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:12:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_address
-- ----------------------------
DROP TABLE IF EXISTS `mall_address`;
CREATE TABLE `mall_address`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '收货地址ID',
  `member_id` bigint UNSIGNED NOT NULL COMMENT '会员ID',
  `province_id` bigint UNSIGNED NOT NULL COMMENT '省份ID',
  `city_id` bigint UNSIGNED NOT NULL COMMENT '城市ID',
  `district_id` bigint UNSIGNED NOT NULL COMMENT '区县ID',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '具体地址',
  `receiver` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人手机号',
  `is_defaulted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认地址: 0 非默认, 1 默认',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收货地址' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_member
-- ----------------------------
DROP TABLE IF EXISTS `mall_member`;
CREATE TABLE `mall_member`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '会员ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员头像',
  `gender` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '会员性别: 0 保密, 1 男, 2 女',
  `birthday` date NULL DEFAULT NULL COMMENT '会员生日',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_member_address
-- ----------------------------
DROP TABLE IF EXISTS `mall_member_address`;
CREATE TABLE `mall_member_address`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '收货地址ID',
  `member_id` bigint UNSIGNED NOT NULL COMMENT '会员ID',
  `province_id` bigint UNSIGNED NOT NULL COMMENT '省份ID',
  `city_id` bigint UNSIGNED NOT NULL COMMENT '城市ID',
  `district_id` bigint UNSIGNED NOT NULL COMMENT '区县ID',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '具体地址',
  `receiver` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人',
  `receiver_mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人手机号',
  `is_defaulted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认地址: 0 非默认, 1 默认',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员收货地址' ROW_FORMAT = DYNAMIC;
