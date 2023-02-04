<h1 align="center">🏪 mall-cloud</h1>

<p align="center">
<a target="_blank" href="https://github.com/zhouboyi1998/mall-cloud"> 
<img src="https://img.shields.io/github/stars/zhouboyi1998/mall-cloud?logo=github">
</a>
<a target="_blank" href="https://opensource.org/licenses/MIT"> 
<img src="https://img.shields.io/badge/license-MIT-red"> 
</a>
<img src="https://img.shields.io/badge/JDK-1.8-darkcyan">
<img src="https://img.shields.io/badge/Spring Boot-2.3.12.RELEASE-brightgreen">
<img src="https://img.shields.io/badge/Spring Cloud-Hoxton.SR12-brightgreen">
<img src="https://img.shields.io/badge/Spring Cloud Alibaba-2.2.7.RELEASE-brightgreen">
<img src="https://img.shields.io/badge/MyBatis Plus-3.4.1-dodgerblue">
<img src="https://img.shields.io/badge/Swagger2 Knife4J-2.0.9-blue">
<img src="https://img.shields.io/badge/MinIO-8.3.4-crimson">
<img src="https://img.shields.io/badge/FastDFS-1.27.0.0-orange">
<img src="https://img.shields.io/badge/Binlog-0.21.0-dodgerblue">
<img src="https://img.shields.io/badge/Canal-1.1.5-orange">
<img src="https://img.shields.io/badge/RabbitMQ-3.6.5-orange">
<img src="https://img.shields.io/badge/RocketMQ-4.9.3-orange">
<img src="https://img.shields.io/badge/ElasticSearch-7.6.2-mediumturquoise">
</p>

### 📖 语言

简体中文 | [English](./README.en.md)

### 💼 项目模块

```
|-- mall-business (业务模块)
    |-- mall-admin (管理员模块)
    |-- mall-goods (商品模块)
    |-- mall-member (会员模块)
    |-- mall-merchant (商家模块)
    |-- mall-order (订单模块)
    |-- mall-storage (仓库模块)
    |-- mall-system (系统模块)

|-- mall-common (公共包)
    |-- mall-common-constant (常量公共包)
    |-- mall-common-core (核心公共包)
    |-- mall-common-log (日志公共包)
    |-- mall-common-mysql (MySQL 数据库公共包)
    |-- mall-common-rabbitmq (RabbitMQ 消息队列公共包)
    |-- mall-common-redis (Redis 缓存公共包)
    |-- mall-common-rocketmq (RocketMQ 消息队列公共包)
    |-- mall-common-swagger (Swagger API 文档公共包)

|-- mall-middleware (中间件模块)
    |-- mall-binlog (Binlog 数据库监听器)
    |-- mall-canal (Canal 数据库监听器)
    |-- mall-elasticsearch (ElasticSearch 搜索引擎)
    |-- mall-fastdfs (FastDFS 分布式文件存储)
    |-- mall-minio (MinIO 分布式文件存储)
    |-- mall-solr (Solr 搜索引擎)

|-- mall-service (基础服务模块)
    |-- mall-eureka (Eureka 注册中心, 弃用)
    |-- mall-gateway (Spring Cloud Gateway 网关)
    |-- mall-generator (代码生成器)
    |-- mall-id (分布式ID生成器)
    |-- mall-security (Spring Security 安全模块)
    |-- mall-user (用户模块)
```

### 📜 开源协议

[MIT License](https://opensource.org/licenses/MIT) Copyright (c) 2022 周博义
