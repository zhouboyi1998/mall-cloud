<h2 align="center">📔 SkyWalking</h2>

### 📦 Windows 安装

#### SkyWalking APM 8.9.1

* TIP：SkyWalking APM 最新版本在 Windows 可能会无法运行
* 所以这里下载上一个大版本的最后一个小版本


* Powershell 命令行解压 `apache-skywalking-apm-8.9.1.tar.gz`

```bash
tar -zxvf apache-skywalking-apm-8.9.1.tar.gz
```

* 进入 `/webapp` 目录，修改 `webapp.yml` 配置文件

```yaml
server:
  port: 8087
```

* 进入 `/bin` 目录，双击 `startup.bat` 运行
* 运行后会启动两个 `Java` 服务
    * `Skywalking-Collector` 收集器
    * `Skywalking-Webapp` UI 界面


* `SkyWalking APM` 需要占用 `11800`、`12800` 两个端口
    * `11800`：与 `Agent` 进行交互
    * `12800`：与 UI 界面进行交互

---

### 📈 Spring Boot 集成 SkyWalking Agent

#### SkyWalking Agent 9.0.0

* Powershell 命令行解压 `apache-skywalking-java-agent-9.0.0.tgz`

```bash
tar -zxvf apache-skywalking-java-agent-9.0.0.tgz
```

#### IDEA 配置启动参数

* 进入 `Edit Configurations` 页面
* 为每个服务添加以下 `VM Options`
    * `-javaagent`配置的目录替换成 `SkyWalking Agent` 实际安装目录
    * `-Dskywalking.collector.backend_service` 配置替换成 `SkyWalking APM` 实际部署地址

```
-javaagent:D:\Study\SkyWalking\skywalking-agent\skywalking-agent.jar
-Dskywalking.agent.service_name=mall-gateway-service
-Dskywalking.agent.instance_name=mall-gateway-instance
-Dskywalking.collector.backend_service=127.0.0.1:11800
```

---

### 🐳 Docker 部署时添加 SkyWalking Agent

* 复制 `SkyWalking Agent` 的文件夹到项目根目录
* 修改 `Dockerfile` 文件，添加一行 `COPY` 语句，修改 `ENTRYPOINT` 语句

```dockerfile
COPY ./skywalking-agent /mall/skywalking-agent
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=xxx-service", "-Dskywalking.agent.instance_name=xxx-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "xxx.jar", "--spring.profiles.active=docker"]
```
