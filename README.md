<h1 align="center">🏪 mall-cloud</h1>

<p align="center">
<a target="_blank" href="https://github.com/zhouboyi1998/mall-cloud"> 
<img src="https://img.shields.io/github/stars/zhouboyi1998/mall-cloud?logo=github">
</a>
<a target="_blank" href="https://opensource.org/licenses/MIT"> 
<img src="https://img.shields.io/badge/license-MIT-red"> 
</a>
<img src="https://img.shields.io/badge/JDK-8-crimson">
<img src="https://img.shields.io/badge/Spring Boot-2.3.12.RELEASE-brightener">
<img src="https://img.shields.io/badge/Spring Cloud-Hoxton.SR12-brightener">
<img src="https://img.shields.io/badge/Spring Cloud Alibaba-2.2.9.RELEASE-brightener">
</p>

### 📖 自述文档

简体中文 | [English](./README.en.md)

### 💼 项目模块

```
|-- mall-api (远程调用模块)
    ...

|-- mall-business (业务模块)
    |-- mall-goods (商品模块)
    |-- mall-member (会员模块)
    |-- mall-merchant (商家模块)
    |-- mall-order (订单模块)
    |-- mall-system (系统模块)

|-- mall-center (中台模块)
    |-- mall-ordercenter (订单中心)

|-- mall-common (公共包)
    |-- mall-common-apollo (Apollo 公共包)
    |-- mall-common-constant (常量公共包)
    |-- mall-common-consul (Consul 公共包)
    |-- mall-common-core (核心公共包)
    |-- mall-common-jackson (Jackson 公共包)
    |-- mall-common-kafka (Kafka 消息队列公共包)
    |-- mall-common-lang (数据类型公共包)
    |-- mall-common-log (日志公共包)
    |-- mall-common-mybatisplus (MyBatis-Plus 公共包)
    |-- mall-common-nacos (Nacos 公共包)
    |-- mall-common-rabbitmq (RabbitMQ 消息队列公共包)
    |-- mall-common-redis (Redis 公共包)
    |-- mall-common-rocketmq (RocketMQ 消息队列公共包)
    |-- mall-common-swagger (Swagger API 文档公共包)
    |-- mall-common-util (工具公共包)
    |-- mall-common-xxljob (XXL-JOB 分布式任务调度公共包)
    |-- mall-common-zookeeper (Zookeeper 公共包)

|-- mall-middleware (中间件模块)
    |-- mall-binlog (MySQL Binlog Connector 数据库监听器)
    |-- mall-canal (Canal 数据库监听器)
    |-- mall-debezium (Debezium 数据库监听器)
    |-- mall-elasticsearch (ElasticSearch 搜索引擎)
    |-- mall-fastdfs (FastDFS 分布式文件存储)
    |-- mall-minio (MinIO 分布式文件存储)
    |-- mall-qiniu (七牛云对象存储)
    |-- mall-solr (Solr 搜索引擎)

|-- mall-service (基础服务模块)
    |-- mall-admin (Spring Boot Admin 监控模块)
    |-- mall-eureka (Eureka 注册中心)
    |-- mall-gateway (Spring Cloud Gateway 网关模块)
    |-- mall-generator (代码生成器)
    |-- mall-id (ID 生成器)
    |-- mall-security (Spring Security 安全模块)
    |-- mall-user (用户模块)
```

### 🧬 版本管理

| ☸ | ⚛ |
| :--- | :--- |
| 关系型数据库 | <img src="https://img.shields.io/badge/MySQL-8.0.19-royalblue"> <img src="https://img.shields.io/badge/MariaDB-11.1.2-peru"> <img src="https://img.shields.io/badge/PostgreSQL-14.3-royalblue"> <img src="https://img.shields.io/badge/SQL Server-2022-yellow"> |
| KV 数据库 | <img src="https://img.shields.io/badge/Redis-3.2.100-crimson"> |
| 搜索引擎 | <img src="https://img.shields.io/badge/ElasticSearch-7.6.2-darkturquoise"> <img src="https://img.shields.io/badge/Solr-8.11.1-orangered"> |
| ORM | <img src="https://img.shields.io/badge/MyBatis Plus-3.4.1-dodgerblue"> <img src="https://img.shields.io/badge/Spring Data--brightener"> |
| 注册中心 | <img src="https://img.shields.io/badge/Eureka--brightener"> <img src="https://img.shields.io/badge/Nacos-2.0.3-deepskyblue"> <img src="https://img.shields.io/badge/Zookeeper-3.6.3-forestgreen"> <img src="https://img.shields.io/badge/Consul-1.17.0-mediumvioletred"> |
| 配置中心 | <img src="https://img.shields.io/badge/Apollo-2.1.0-darkseagreen"> <img src="https://img.shields.io/badge/Nacos-2.0.3-deepskyblue"> <img src="https://img.shields.io/badge/Zookeeper-3.6.3-forestgreen"> <img src="https://img.shields.io/badge/Consul-1.17.0-mediumvioletred"> |
| 远程调用 | <img src="https://img.shields.io/badge/OpenFeign--brightener"> |
| 网关 | <img src="https://img.shields.io/badge/Spring Cloud Gateway--brightener"> |
| 安全 | <img src="https://img.shields.io/badge/Spring Security--brightener"> <img src="https://img.shields.io/badge/Spring Cloud Oauth2--brightener"> <img src="https://img.shields.io/badge/Nimbus JOSE JWT-8.16-deepskyblue"> |
| 服务流控 | <img src="https://img.shields.io/badge/Hystrix--brightener"> <img src="https://img.shields.io/badge/Sentinel-1.8.1-royalblue"> |
| 分布式事务 | <img src="https://img.shields.io/badge/Seata-1.5.2-royalblue"> |
| API 文档 | <img src="https://img.shields.io/badge/Knife4J-2.0.9-orangered"> |
| 代码生成器 | <img src="https://img.shields.io/badge/MyBatis Generator-1.4.0-crimson"> <img src="https://img.shields.io/badge/MyBatis Plus Generator-3.4.1-dodgerblue"> |
| 数据库监听器 | <img src="https://img.shields.io/badge/MySQL Binlog Connector-0.21.0-royalblue"> <img src="https://img.shields.io/badge/Canal-1.1.5-darkorange"> <img src="https://img.shields.io/badge/Debezium-1.5.4.Final-mediumspringgreen"> |
| 消息队列 | <img src="https://img.shields.io/badge/RabbitMQ-3.6.5-orange"> <img src="https://img.shields.io/badge/RocketMQ-4.9.3-darkorange"> <img src="https://img.shields.io/badge/Kafka-3.4.0-mediumturquoise"> |
| 对象存储 | <img src="https://img.shields.io/badge/MinIO-8.3.4-crimson"> <img src="https://img.shields.io/badge/FastDFS-1.27.0.0-darkorange"> |
| 验证码生成器 | <img src="https://img.shields.io/badge/Kaptcha-1.1.0-blue"> <img src="https://img.shields.io/badge/Easy Captcha-1.6.2-blue"> <img src="https://img.shields.io/badge/Hutool Captcha-5.0.4-blue"> |
| 任务调度 | <img src="https://img.shields.io/badge/XXL JOB-2.4.0-mediumseagreen"> |
| 监控告警 | <img src="https://img.shields.io/badge/Prometheus-2.43.0-orange"> <img src="https://img.shields.io/badge/Spring Boot Admin-2.3.1-seagreen"> |
| 链路追踪 | <img src="https://img.shields.io/badge/SkyWalking-9.0.0-blue"> <img src="https://img.shields.io/badge/Sleuth--brightener"> <img src="https://img.shields.io/badge/Zipkin-2.24.3-orange"> |
| 容器化 | <img src="https://img.shields.io/badge/Docker--deepskyblue"> |
| 可视化 | <img src="https://img.shields.io/badge/Kibana-7.6.2-darkturquoise"> <img src="https://img.shields.io/badge/ElasticSearch Head--darkturquoise"> <img src="https://img.shields.io/badge/Kafka UI Lite-1.2.11-deepgreen"> |

### 📜 开源协议

[MIT License](https://opensource.org/licenses/MIT) Copyright (c) 2022 zhouboyi
