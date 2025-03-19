--
-- Copyright 2022 Apollo Authors
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--
/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

# Create Database
# ------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS ApolloPortalDB DEFAULT CHARACTER SET = utf8mb4;

USE ApolloPortalDB;

# Dump of table app
# ------------------------------------------------------------

DROP TABLE IF EXISTS `App`;

CREATE TABLE `App`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `AppId`                     VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `Name`                      VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT '应用名',
    `OrgId`                     VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT '部门Id',
    `OrgName`                   VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '部门名字',
    `OwnerName`                 VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'ownerName',
    `OwnerEmail`                VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'ownerEmail',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_AppId_DeletedAt` (`AppId`, `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_Name` (`Name`(191))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='应用表';



# Dump of table appnamespace
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AppNamespace`;

CREATE TABLE `AppNamespace`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `Name`                      VARCHAR(32)      NOT NULL DEFAULT '' COMMENT 'namespace名字，注意，需要全局唯一',
    `AppId`                     VARCHAR(64)      NOT NULL DEFAULT '' COMMENT 'app id',
    `Format`                    VARCHAR(32)      NOT NULL DEFAULT 'properties' COMMENT 'namespace的format类型',
    `IsPublic`                  BIT(1)           NOT NULL DEFAULT b'0' COMMENT 'namespace是否为公共',
    `Comment`                   VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '注释',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_AppId_Name_DeletedAt` (`AppId`, `Name`, `DeletedAt`),
    KEY `Name_AppId` (`Name`, `AppId`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='应用namespace定义';



# Dump of table consumer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Consumer`;

CREATE TABLE `Consumer`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `AppId`                     VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `Name`                      VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT '应用名',
    `OrgId`                     VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT '部门Id',
    `OrgName`                   VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '部门名字',
    `OwnerName`                 VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'ownerName',
    `OwnerEmail`                VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'ownerEmail',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_AppId_DeletedAt` (`AppId`, `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='开放API消费者';



# Dump of table consumeraudit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ConsumerAudit`;

CREATE TABLE `ConsumerAudit`
(
    `Id`                     INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `ConsumerId`             INT(11) UNSIGNED          DEFAULT NULL COMMENT 'Consumer Id',
    `Uri`                    VARCHAR(1024)    NOT NULL DEFAULT '' COMMENT '访问的Uri',
    `Method`                 VARCHAR(16)      NOT NULL DEFAULT '' COMMENT '访问的Method',
    `DataChange_CreatedTime` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastTime`    TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_ConsumerId` (`ConsumerId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='consumer审计表';



# Dump of table consumerrole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ConsumerRole`;

CREATE TABLE `ConsumerRole`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `ConsumerId`                INT(11) UNSIGNED          DEFAULT NULL COMMENT 'Consumer Id',
    `RoleId`                    INT(10) UNSIGNED          DEFAULT NULL COMMENT 'Role Id',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_ConsumerId_RoleId_DeletedAt` (`ConsumerId`, `RoleId`, `DeletedAt`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_RoleId` (`RoleId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='consumer和role的绑定表';



# Dump of table consumertoken
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ConsumerToken`;

CREATE TABLE `ConsumerToken`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `ConsumerId`                INT(11) UNSIGNED          DEFAULT NULL COMMENT 'ConsumerId',
    `Token`                     VARCHAR(128)     NOT NULL DEFAULT '' COMMENT 'token',
    `Expires`                   DATETIME         NOT NULL DEFAULT '2099-01-01 00:00:00' COMMENT 'token失效时间',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_Token_DeletedAt` (`Token`, `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='consumer token表';

# Dump of table favorite
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Favorite`;

CREATE TABLE `Favorite`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `UserId`                    VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT '收藏的用户',
    `AppId`                     VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `Position`                  INT(32)          NOT NULL DEFAULT '10000' COMMENT '收藏顺序',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_UserId_AppId_DeletedAt` (`UserId`, `AppId`, `DeletedAt`),
    KEY `AppId` (`AppId`(191)),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8mb4 COMMENT ='应用收藏表';

# Dump of table permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Permission`;

CREATE TABLE `Permission`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `PermissionType`            VARCHAR(32)      NOT NULL DEFAULT '' COMMENT '权限类型',
    `TargetId`                  VARCHAR(256)     NOT NULL DEFAULT '' COMMENT '权限对象类型',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_TargetId_PermissionType_DeletedAt` (`TargetId`, `PermissionType`, `DeletedAt`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='permission表';



# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Role`;

CREATE TABLE `Role`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `RoleName`                  VARCHAR(256)     NOT NULL DEFAULT '' COMMENT 'Role name',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_RoleName_DeletedAt` (`RoleName`, `DeletedAt`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';



# Dump of table rolepermission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `RolePermission`;

CREATE TABLE `RolePermission`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `RoleId`                    INT(10) UNSIGNED          DEFAULT NULL COMMENT 'Role Id',
    `PermissionId`              INT(10) UNSIGNED          DEFAULT NULL COMMENT 'Permission Id',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_RoleId_PermissionId_DeletedAt` (`RoleId`, `PermissionId`, `DeletedAt`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_PermissionId` (`PermissionId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色和权限的绑定表';



# Dump of table serverconfig
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ServerConfig`;

CREATE TABLE `ServerConfig`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `Key`                       VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '配置项Key',
    `Value`                     VARCHAR(2048)    NOT NULL DEFAULT 'default' COMMENT '配置项值',
    `Comment`                   VARCHAR(1024)             DEFAULT '' COMMENT '注释',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_Key_DeletedAt` (`Key`, `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='配置服务自身配置';



# Dump of table userrole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserRole`;

CREATE TABLE `UserRole`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `UserId`                    VARCHAR(128)              DEFAULT '' COMMENT '用户身份标识',
    `RoleId`                    INT(10) UNSIGNED          DEFAULT NULL COMMENT 'Role Id',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_UserId_RoleId_DeletedAt` (`UserId`, `RoleId`, `DeletedAt`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_RoleId` (`RoleId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户和role的绑定表';

# Dump of table Users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users`
(
    `Id`              INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `Username`        VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '用户登录账户',
    `Password`        VARCHAR(512)     NOT NULL DEFAULT 'default' COMMENT '密码',
    `UserDisplayName` VARCHAR(512)     NOT NULL DEFAULT 'default' COMMENT '用户名称',
    `Email`           VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '邮箱地址',
    `Enabled`         TINYINT(4)                DEFAULT NULL COMMENT '是否有效',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_Username` (`Username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';


# Dump of table Authorities
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Authorities`;

CREATE TABLE `Authorities`
(
    `Id`        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `Username`  VARCHAR(64)      NOT NULL,
    `Authority` VARCHAR(50)      NOT NULL,
    PRIMARY KEY (`Id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


# Config
# ------------------------------------------------------------
INSERT INTO `ServerConfig` (`Key`, `Value`, `Comment`)
VALUES ('apollo.portal.envs', 'dev', '可支持的环境列表'),
       ('organizations', '[{\"orgId\":\"TEST1\",\"orgName\":\"样例部门1\"},{\"orgId\":\"TEST2\",\"orgName\":\"样例部门2\"}]', '部门列表'),
       ('superAdmin', 'apollo', 'Portal超级管理员'),
       ('api.readTimeout', '10000', 'http接口read timeout'),
       ('consumer.token.salt', 'someSalt', 'consumer token salt'),
       ('admin.createPrivateNamespace.switch', 'true', '是否允许项目管理员创建私有namespace'),
       ('configView.memberOnly.envs', 'pro', '只对项目成员显示配置信息的环境列表，多个env以英文逗号分隔'),
       ('apollo.portal.meta.servers', '{}', '各环境Meta Service列表');


INSERT INTO `Users` (`Username`, `Password`, `UserDisplayName`, `Email`, `Enabled`)
VALUES ('apollo', '$2a$10$7r20uS.BQ9uBpf3Baj3uQOZvMVvB1RN3PYoKE94gtz2.WAOuiiwXS', 'apollo', 'apollo@acme.com', 1);

INSERT INTO `Authorities` (`Username`, `Authority`) VALUES ('apollo', 'ROLE_user');

-- spring session (https://github.com/spring-projects/spring-session/blob/faee8f1bdb8822a5653a81eba838dddf224d92d6/spring-session-jdbc/src/main/resources/org/springframework/session/jdbc/schema-mysql.sql)
CREATE TABLE SPRING_SESSION
(
    PRIMARY_ID            CHAR(36) NOT NULL,
    SESSION_ID            CHAR(36) NOT NULL,
    CREATION_TIME         BIGINT   NOT NULL,
    LAST_ACCESS_TIME      BIGINT   NOT NULL,
    MAX_INACTIVE_INTERVAL INT      NOT NULL,
    EXPIRY_TIME           BIGINT   NOT NULL,
    PRINCIPAL_NAME        VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE = InnoDB
  ROW_FORMAT = DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID CHAR(36)     NOT NULL,
    ATTRIBUTE_NAME     VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES    BLOB         NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
) ENGINE = InnoDB
  ROW_FORMAT = DYNAMIC;

/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
