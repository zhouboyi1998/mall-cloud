<h2 align="center">📔 RocketMQ</h2>

### 🪒 Windows 安装

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

#### RocketMQ Dashboard

* 解压 `rocketmq-dashboard-rocketmq-dashboard-1.0.0.zip`
* 进入 `/src/main/resources` 目录
* 修改 `application.properties` 配置文件

```properties
# 控制台访问端口
server.port=8085
# 配置 NameServer 地址
rocketmq.config.namesrvAddr=127.0.0.1:9876
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
