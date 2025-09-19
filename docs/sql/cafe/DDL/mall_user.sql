/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 127.0.0.1 3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_user

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/09/2025 00:12:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_platform
-- ----------------------------
DROP TABLE IF EXISTS `mall_platform`;
CREATE TABLE `mall_platform`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '平台ID',
  `client_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端ID',
  `platform_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '平台名称',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_resource
-- ----------------------------
DROP TABLE IF EXISTS `mall_resource`;
CREATE TABLE `mall_resource`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '菜单ID',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级菜单ID',
  `platform_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '平台ID',
  `resource_type` int NOT NULL COMMENT '资源类型: 1 菜单, 2 按钮, 3 API',
  `resource_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源名称',
  `resource_title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源标题',
  `resource_icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源图标',
  `resource_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源内容: 菜单路径, API 路径',
  `level` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '资源层级: 1 一级资源, 2 二级资源, 3 三级资源',
  `sort` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_role
-- ----------------------------
DROP TABLE IF EXISTS `mall_role`;
CREATE TABLE `mall_role`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `platform_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '平台ID',
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述信息',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `mall_role_resource`;
CREATE TABLE `mall_role_resource`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '角色-菜单关联关系ID',
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `resource_id` bigint UNSIGNED NOT NULL COMMENT '资源ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色-资源关联关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 0 禁用, 1 正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0 未删除, 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_user_platform
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_platform`;
CREATE TABLE `mall_user_platform`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '用户-平台关联关系ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `platform_id` bigint UNSIGNED NOT NULL COMMENT '平台ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-平台关联关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mall_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_role`;
CREATE TABLE `mall_user_role`  (
  `id` bigint UNSIGNED NOT NULL COMMENT '用户-角色关联关系ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-角色关联关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
