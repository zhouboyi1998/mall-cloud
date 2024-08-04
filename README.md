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

### 📜 开源协议

[MIT License](https://opensource.org/licenses/MIT) Copyright (c) 2022 zhouboyi

### 💼 项目模块

```
|-- mall-api (远程调用模块)
    ...

|-- mall-business (业务模块)
    |-- mall-foundation (基座模块)
    |-- mall-goods (商品模块)
    |-- mall-manager (管理员模块)
    |-- mall-member (会员模块)
    |-- mall-merchant (商家模块)
    |-- mall-order (订单模块)
    |-- mall-storage (仓储模块)

|-- mall-center (中台模块)
    |-- mall-goodscenter (商品中心)
    |-- mall-openapicenter (开放接口中心)
    |-- mall-ordercenter (订单中心)

|-- mall-common (公共包)
    |-- mall-common-constant (常量公共包)
    |-- mall-common-core (核心公共包)
    |-- mall-common-jackson (Jackson 公共包)
    |-- mall-common-kafka (Kafka 消息队列公共包)
    |-- mall-common-lang (数据类型公共包)
    |-- mall-common-log (日志公共包)
    |-- mall-common-mybatisplus (MyBatis-Plus 公共包)
    |-- mall-common-rabbitmq (RabbitMQ 消息队列公共包)
    |-- mall-common-redis (Redis 公共包)
    |-- mall-common-rocketmq (RocketMQ 消息队列公共包)
    |-- mall-common-swagger (Swagger API 文档公共包)
    |-- mall-common-util (工具公共包)
    |-- mall-common-xxljob (XXL-JOB 分布式任务调度公共包)

|-- mall-configuration (配置中心公共包)
    |-- mall-configuration-apollo (Apollo 配置中心公共包)
    |-- mall-configuration-config (Spring Cloud Config 配置中心公共包)
    |-- mall-configuration-consul (Consul 配置中心公共包)
    |-- mall-configuration-nacos (Nacos 配置中心公共包)
    |-- mall-configuration-zookeeper (Zookeeper 配置中心公共包)

|-- mall-discovery (注册中心公共包)
    |-- mall-discovery-consul (Consul 注册中心公共包)
    |-- mall-discovery-eureka (Eureka 注册中心公共包)
    |-- mall-discovery-nacos (Nacos 注册中心公共包)
    |-- mall-discovery-zookeeper (Zookeeper 注册中心公共包)

|-- mall-middleware (中间件模块)
    |-- mall-binlog (MySQL Binlog Connector 数据库监听器)
    |-- mall-canal (Canal 数据库监听器)
    |-- mall-clickhouse (ClickHouse 列式数据库)
    |-- mall-debezium (Debezium 数据库监听器)
    |-- mall-elasticsearch (ElasticSearch 搜索引擎)
    |-- mall-fastdfs (FastDFS 分布式文件存储)
    |-- mall-minio (MinIO 分布式文件存储)
    |-- mall-qiniu (七牛云对象存储)
    |-- mall-solr (Solr 搜索引擎)

|-- mall-service (基础服务模块)
    |-- mall-admin (Spring Boot Admin 监控模块)
    |-- mall-config (Spring Cloud Config 配置中心服务端)
    |-- mall-eureka (Eureka 注册中心服务端)
    |-- mall-gateway (Spring Cloud Gateway 网关模块)
    |-- mall-generator (代码生成器)
    |-- mall-id (ID 生成器)
    |-- mall-security (Spring Security 安全模块)
    |-- mall-swagger (Swagger API 文档聚合模块)
    |-- mall-user (用户模块)
```
