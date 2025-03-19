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
CREATE DATABASE IF NOT EXISTS ApolloConfigDB DEFAULT CHARACTER SET = utf8mb4;

USE ApolloConfigDB;

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



# Dump of table audit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Audit`;

CREATE TABLE `Audit`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `EntityName`                VARCHAR(50)      NOT NULL DEFAULT 'default' COMMENT '表名',
    `EntityId`                  INT(10) UNSIGNED          DEFAULT NULL COMMENT '记录ID',
    `OpName`                    VARCHAR(50)      NOT NULL DEFAULT 'default' COMMENT '操作类型',
    `Comment`                   VARCHAR(500)              DEFAULT NULL COMMENT '备注',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='日志审计表';



# Dump of table cluster
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Cluster`;

CREATE TABLE `Cluster`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `Name`                      VARCHAR(32)      NOT NULL DEFAULT '' COMMENT '集群名字',
    `AppId`                     VARCHAR(64)      NOT NULL DEFAULT '' COMMENT 'App id',
    `ParentClusterId`           INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '父cluster',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_AppId_Name_DeletedAt` (`AppId`, `Name`, `DeletedAt`),
    KEY `IX_ParentClusterId` (`ParentClusterId`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='集群';



# Dump of table commit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Commit`;

CREATE TABLE `Commit`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `ChangeSets`                LONGTEXT         NOT NULL COMMENT '修改变更集',
    `AppId`                     VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `ClusterName`               VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
    `NamespaceName`             VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
    `Comment`                   VARCHAR(500)              DEFAULT NULL COMMENT '备注',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`),
    KEY `AppId` (`AppId`(191)),
    KEY `ClusterName` (`ClusterName`(191)),
    KEY `NamespaceName` (`NamespaceName`(191))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='commit 历史表';

# Dump of table grayreleaserule
# ------------------------------------------------------------

DROP TABLE IF EXISTS `GrayReleaseRule`;

CREATE TABLE `GrayReleaseRule`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `AppId`                     VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `ClusterName`               VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT 'Cluster Name',
    `NamespaceName`             VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT 'Namespace Name',
    `BranchName`                VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT 'branch name',
    `Rules`                     VARCHAR(16000)            DEFAULT '[]' COMMENT '灰度规则',
    `ReleaseId`                 INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '灰度对应的release',
    `BranchStatus`              TINYINT(2)                DEFAULT '1' COMMENT '灰度分支状态: 0:删除分支,1:正在使用的规则 2：全量发布',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_Namespace` (`AppId`, `ClusterName`, `NamespaceName`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='灰度规则表';


# Dump of table instance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Instance`;

CREATE TABLE `Instance`
(
    `Id`                     INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `AppId`                  VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `ClusterName`            VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
    `DataCenter`             VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT 'Data Center Name',
    `Ip`                     VARCHAR(32)      NOT NULL DEFAULT '' COMMENT 'instance ip',
    `DataChange_CreatedTime` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `IX_UNIQUE_KEY` (`AppId`, `ClusterName`, `Ip`, `DataCenter`),
    KEY `IX_IP` (`Ip`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='使用配置的应用实例';



# Dump of table instanceconfig
# ------------------------------------------------------------

DROP TABLE IF EXISTS `InstanceConfig`;

CREATE TABLE `InstanceConfig`
(
    `Id`                     INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `InstanceId`             INT(11) UNSIGNED          DEFAULT NULL COMMENT 'Instance Id',
    `ConfigAppId`            VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT 'Config App Id',
    `ConfigClusterName`      VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT 'Config Cluster Name',
    `ConfigNamespaceName`    VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT 'Config Namespace Name',
    `ReleaseKey`             VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '发布的Key',
    `ReleaseDeliveryTime`    TIMESTAMP        NULL     DEFAULT NULL COMMENT '配置获取时间',
    `DataChange_CreatedTime` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `IX_UNIQUE_KEY` (`InstanceId`, `ConfigAppId`, `ConfigNamespaceName`),
    KEY `IX_ReleaseKey` (`ReleaseKey`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_Valid_Namespace` (`ConfigAppId`, `ConfigClusterName`, `ConfigNamespaceName`, `DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='应用实例的配置信息';



# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Item`;

CREATE TABLE `Item`
(
    `Id`                        INT(10) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `NamespaceId`               INT(10) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '集群NamespaceId',
    `Key`                       VARCHAR(128)        NOT NULL DEFAULT 'default' COMMENT '配置项Key',
    `Type`                      TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '配置项类型，0: String，1: Number，2: Boolean，3: JSON',
    `Value`                     LONGTEXT            NOT NULL COMMENT '配置项值',
    `Comment`                   VARCHAR(1024)                DEFAULT '' COMMENT '注释',
    `LineNum`                   INT(10) UNSIGNED             DEFAULT '0' COMMENT '行号',
    `IsDeleted`                 BIT(1)              NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)          NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)         NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)                  DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP           NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    KEY `IX_GroupId` (`NamespaceId`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='配置项目';



# Dump of table namespace
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Namespace`;

CREATE TABLE `Namespace`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `AppId`                     VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `ClusterName`               VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'Cluster Name',
    `NamespaceName`             VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'Namespace Name',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_AppId_ClusterName_NamespaceName_DeletedAt` (`AppId`(191), `ClusterName`(191), `NamespaceName`(191), `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_NamespaceName` (`NamespaceName`(191))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='命名空间';



# Dump of table namespacelock
# ------------------------------------------------------------

DROP TABLE IF EXISTS `NamespaceLock`;

CREATE TABLE `NamespaceLock`
(
    `Id`                        INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `NamespaceId`               INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '集群NamespaceId',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    `IsDeleted`                 BIT(1)                    DEFAULT b'0' COMMENT '软删除',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_NamespaceId_DeletedAt` (`NamespaceId`, `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='namespace的编辑锁';



# Dump of table release
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Release`;

CREATE TABLE `Release`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `ReleaseKey`                VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '发布的Key',
    `Name`                      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '发布名字',
    `Comment`                   VARCHAR(256)              DEFAULT NULL COMMENT '发布说明',
    `AppId`                     VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `ClusterName`               VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
    `NamespaceName`             VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
    `Configurations`            LONGTEXT         NOT NULL COMMENT '发布配置',
    `IsAbandoned`               BIT(1)           NOT NULL DEFAULT b'0' COMMENT '是否废弃',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_ReleaseKey_DeletedAt` (`ReleaseKey`, `DeletedAt`),
    KEY `AppId_ClusterName_GroupName` (`AppId`(191), `ClusterName`(191), `NamespaceName`(191)),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='发布';


# Dump of table releasehistory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ReleaseHistory`;

CREATE TABLE `ReleaseHistory`
(
    `Id`                        INT(11) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `AppId`                     VARCHAR(64)         NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `ClusterName`               VARCHAR(32)         NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
    `NamespaceName`             VARCHAR(32)         NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
    `BranchName`                VARCHAR(32)         NOT NULL DEFAULT 'default' COMMENT '发布分支名',
    `ReleaseId`                 INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '关联的Release Id',
    `PreviousReleaseId`         INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '前一次发布的ReleaseId',
    `Operation`                 TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '发布类型，0: 普通发布，1: 回滚，2: 灰度发布，3: 灰度规则更新，4: 灰度合并回主分支发布，5: 主分支发布灰度自动发布，6: 主分支回滚灰度自动发布，7: 放弃灰度',
    `OperationContext`          LONGTEXT            NOT NULL COMMENT '发布上下文信息',
    `IsDeleted`                 BIT(1)              NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)          NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)         NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)                  DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP           NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    KEY `IX_Namespace` (`AppId`, `ClusterName`, `NamespaceName`, `BranchName`),
    KEY `IX_ReleaseId` (`ReleaseId`),
    KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_PreviousReleaseId` (`PreviousReleaseId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='发布历史';


# Dump of table releasemessage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ReleaseMessage`;

CREATE TABLE `ReleaseMessage`
(
    `Id`                  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `Message`             VARCHAR(1024)    NOT NULL DEFAULT '' COMMENT '发布的消息内容',
    `DataChange_LastTime` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`),
    KEY `IX_Message` (`Message`(191))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='发布消息';



# Dump of table serverconfig
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ServerConfig`;

CREATE TABLE `ServerConfig`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `Key`                       VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '配置项Key',
    `Cluster`                   VARCHAR(32)      NOT NULL DEFAULT 'default' COMMENT '配置对应的集群，default为不针对特定的集群',
    `Value`                     VARCHAR(2048)    NOT NULL DEFAULT 'default' COMMENT '配置项值',
    `Comment`                   VARCHAR(1024)             DEFAULT '' COMMENT '注释',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_Key_Cluster_DeletedAt` (`Key`, `Cluster`, `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='配置服务自身配置';

# Dump of table accesskey
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AccessKey`;

CREATE TABLE `AccessKey`
(
    `Id`                        INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `AppId`                     VARCHAR(500)     NOT NULL DEFAULT 'default' COMMENT 'AppID',
    `Secret`                    VARCHAR(128)     NOT NULL DEFAULT '' COMMENT 'Secret',
    `IsEnabled`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: enabled, 0: disabled',
    `IsDeleted`                 BIT(1)           NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
    `DeletedAt`                 BIGINT(20)       NOT NULL DEFAULT '0' COMMENT 'Delete timestamp based on milliseconds',
    `DataChange_CreatedBy`      VARCHAR(64)      NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
    `DataChange_CreatedTime`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `DataChange_LastModifiedBy` VARCHAR(64)               DEFAULT '' COMMENT '最后修改人邮箱前缀',
    `DataChange_LastTime`       TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `UK_AppId_Secret_DeletedAt` (`AppId`, `Secret`, `DeletedAt`),
    KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='访问密钥';


# Dump of table serviceregistry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ServiceRegistry`;

CREATE TABLE `ServiceRegistry`
(
    `Id`                     INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `ServiceName`            VARCHAR(64)      NOT NULL COMMENT '服务名',
    `Uri`                    VARCHAR(64)      NOT NULL COMMENT '服务地址',
    `Cluster`                VARCHAR(64)      NOT NULL COMMENT '集群，可以用来标识apollo.cluster或者网络分区',
    `Metadata`               VARCHAR(1024)    NOT NULL DEFAULT '{}' COMMENT '元数据，key value结构的json object，为了方面后面扩展功能而不需要修改表结构',
    `DataChange_CreatedTime` TIMESTAMP        NOT NULL COMMENT '创建时间',
    `DataChange_LastTime`    TIMESTAMP        NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`Id`),
    UNIQUE INDEX `IX_UNIQUE_KEY` (`ServiceName`, `Uri`),
    INDEX `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='注册中心';


# Config
# ------------------------------------------------------------
INSERT INTO `ServerConfig` (`Key`, `Cluster`, `Value`, `Comment`)
VALUES ('eureka.service.url', 'default', 'http://localhost:8080/eureka/', 'Eureka服务Url，多个service以英文逗号分隔'),
       ('namespace.lock.switch', 'default', 'false', '一次发布只能有一个人修改开关'),
       ('item.key.length.limit', 'default', '128', 'item key 最大长度限制'),
       ('item.value.length.limit', 'default', '20000', 'item value最大长度限制'),
       ('config-service.cache.enabled', 'default', 'false', 'ConfigService是否开启缓存，开启后能提高性能，但是会增大内存消耗！');

/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
