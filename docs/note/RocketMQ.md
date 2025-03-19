<h2 align="center">📔 RocketMQ</h2>

### 📦 Windows 安装

#### RocketMQ 4.9.3

* 解压 `rocketmq-all-4.9.3-bin-release.zip`
* 命令行进入 `/bin` 目录
* 启动 `NameServer`

```bash
start mqnamesrv.cmd
```

* 启动 `Broker`

```bash
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
```

###### 添加环境变量

* 系统变量
    * 变量名：`ROCKETMQ_HOME`
    * 变量值：`RocketMQ` 安装目录
* 系统变量 `Path`
    * `%ROCKETMQ_HOME%\bin`

#### RocketMQ Dashboard

* 解压 `rocketmq-dashboard-rocketmq-dashboard-1.0.0.zip`
* 进入 `/src/main/resources` 目录
* 修改 `application.properties` 配置文件

```properties
# 控制台访问端口
server.port = 8085
# 配置 NameServer 地址
rocketmq.config.namesrvAddr = 127.0.0.1:9876
```

* 根目录中打包

```bash
mvn clean package -Dmaven.test.skip=true
```

* 运行

```bash
java -jar rocketmq-dashboard-1.0.0.jar
```

#### 批处理文件启动

* 新建 `.bat` 批处理文件，输入以下内容并保存

```bat
@echo off
title Rocket 4.9.3

cd ./rocketmq-all-4.9.3-bin-release/bin
start mqnamesrv.cmd
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true

cd ..
cd ..

cd ./rocketmq-dashboard-rocketmq-dashboard-1.0.0/target
java -jar rocketmq-dashboard-1.0.0.jar

pause
```

---

### 🧰 模块搭建

#### 配置文件

```yaml
# RocketMQ 配置
rocketmq:
  name-server: 127.0.0.1:9876
  # 生产者配置
  producer:
    # 默认生产者组
    group: ${spring.application.name}
```

* `rocketmq.name-server`：**`RocketMQ` 服务端地址**
* `rocketmq.producer.group`：**默认生产者组**
    * `RocketMQ` 客户端包中提供了一个默认生产者 `DefaultMQProducer`
    * 因为需要为这个生产者 `Bean` 指定一个生产者组
    * 所以在配置文件中需要添加**默认生产者组**配置
    * 如果没有配置，在创建这个 `Bean` 的时候会抛出异常，服务启动失败
    * `RocketMQTemplate` 中默认依赖的生产者就是 `DefaultMQProducer`
