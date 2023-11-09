<h2 align="center">📔 Seata</h2>

### 📦 Windows 安装

* 项目使用的 `Spring Cloud Alibaba` 版本是 `2.2.9.RELEASE`
* 其中依赖的 `Seata Client` 版本是 `1.5.2`
* 从 [**GitHub**](https://github.com/seata/seata/releases) 下载 `1.5.2` 版本的 `Seata Server`

---

### 🧰 服务端部署

#### 存储模式

* 存储模式选择 `MySQL`
* 在 `MySQL` 中创建一个名为 `seata` 数据库
* 执行初始化 `SQL`：[**https://github.com/seata/seata/blob/1.5.2/script/server/db/mysql.sql**](https://github.com/seata/seata/blob/1.5.2/script/server/db/mysql.sql)

```mysql
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_status_gmt_modified` (`status`, `gmt_modified`),
    KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(128),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `status`         TINYINT      NOT NULL DEFAULT '0' COMMENT '0:locked ,1:rollbacking',
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_status` (`status`),
    KEY `idx_branch_id` (`branch_id`),
    KEY `idx_xid_and_branch_id` (`xid`, `branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `distributed_lock`
(
    `lock_key`   CHAR(20)    NOT NULL,
    `lock_value` VARCHAR(20) NOT NULL,
    `expire`     BIGINT,
    primary key (`lock_key`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('AsyncCommitting', ' ', 0);
INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('RetryCommitting', ' ', 0);
INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('RetryRollbacking', ' ', 0);
INSERT INTO `distributed_lock` (lock_key, lock_value, expire) VALUES ('TxTimeoutCheck', ' ', 0);
```

#### 配置中心

* 选择 `Nacos` 作为配置中心
* 在 `public` 命名空间下添加配置文件
    * Data ID：`seataServer.properties`
    * Group：`SEATA_GROUP`
    * 配置格式：`Properties`
    * 配置内容：[**https://github.com/seata/seata/blob/1.5.2/script/config-center/config.txt**](https://github.com/seata/seata/blob/1.5.2/script/config-center/config.txt)
* 修改配置内容中的存储模式配置

```properties
store.mode = db
store.lock.mode = db
store.session.mode = db
store.db.dbType = mysql
store.db.driverClassName = com.mysql.cj.jdbc.Driver
store.db.url = jdbc:mysql://127.0.0.1:3306/seata?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true
store.db.user = root
store.db.password = 123456
```

* 完整配置：[**seataServer.properties**](../nacos/SEATA_GROUP/seataServer.properties)

#### 配置文件

* 修改 `seata\conf\application.yml` 配置文件
    * 配置中心使用 `Nacos`
    * 注册中心信息从 `Nacos` 中获取
    * 删除存储模式配置，从 `seataServer.properties` 中读取

```yaml
server:
  port: 7091

spring:
  application:
    name: seata-server

logging:
  config: classpath:logback-spring.xml
  file:
    path: ${user.home}/logs/seata
  extend:
    logstash-appender:
      destination: 127.0.0.1:4560
    kafka-appender:
      bootstrap-servers: 127.0.0.1:9092
      topic: logback_to_logstash

console:
  user:
    username: seata
    password: seata

seata:
  config:
    # support: nacos, consul, apollo, zk, etcd3
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      namespace:
      group: SEATA_GROUP
      username: nacos
      password: nacos
      data-id: seataServer.properties
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos
    preferred-networks: 30.240.*
    nacos:
      server-addr: 127.0.0.1:8848
      namespace:
      group: SEATA_GROUP
      cluster: default
      username: nacos
      password: nacos
      application: seata-server
  security:
    secretKey: SeataSecretKey0c382ef121d778043159209298fd40bf3850a017
    tokenValidityInMilliseconds: 1800000
    ignore:
      urls: /,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/api/v1/auth/login
```

* 如果需要使用其它配置中心、注册中心
* 可以参考 `seata\conf\application.example.yml` 配置文件来做修改

#### 运行

* 运行 `seata\bin\seata-server.bat` 批处理文件
* 访问 `Seata` 控制台：`127.0.0.1:7091`
* 默认用户名 / 密码：`seata` / `seata`

---

### 🌌 客户端环境准备

#### undo_log

* 所有业务数据库新增 `undo_log` 回滚表
* 回滚表 `SQL`：[**https://github.com/seata/seata/blob/1.5.2/script/client/at/db/mysql.sql**](https://github.com/seata/seata/blob/1.5.2/script/client/at/db/mysql.sql)

```mysql
-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';
```

#### 引入依赖

```xml
<!-- Seata 分布式事务 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
</dependency>
```

#### 客户端配置

```yaml
# Seata 配置
seata:
  enabled: true
  application-id: ${spring.application.name}
  # 客户端事务组名称
  tx-service-group: default_tx_group
  # 事务模式: AT (默认)
  data-source-proxy-mode: AT
  # 客户端事务组与服务端集群的映射关系
  service:
    vgroup-mapping:
      default_tx_group: default
  # Seata 服务端地址
  registry:
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      namespace:
      group: SEATA_GROUP
      username: nacos
      password: nacos
      application: seata-server
```

#### 无法找到 Feign$Builder

* 如果同时开启 `Hystrix` 和 `Sentinel`
* `Spring` 容器中将会存在两个 `Feign$Builder` 类型的 `Bean`
    * `HystrixFeign$Builder`
    * `SentinelFeign$Builder`
* 导致 `Seata` 无法获取具体的 `Bean`，服务启动失败
* 所以需要关闭其中一个

```yaml
feign:
  hystrix: false
  # OR
  sentinel: false
```

#### 使用分布式事务

* 在主业务逻辑方法上添加注解 `@GlobalTransactional`
