<h2 align="center">📔 Prometheus</h2>

### 📦 Windows 安装

* 从 [**GitHub**](https://github.com/prometheus/prometheus/releases/tag/v2.43.0) 下载 `2.43.0` 版本的 `Prometheus`
* `prometheus-2.43.0.windows-arm64.zip `

---

### 🧰 服务端

#### 修改 prometheus.yml 配置文件

* 在 `scrape_configs` 下添加节点

```yaml
scrape_configs:

  - job_name: "prometheus"
    static_configs:
      - targets: [ "127.0.0.1:9090" ]

  - job_name: "mall-gateway"
    static_configs:
      - targets: [ "127.0.0.1:18071" ]
    metrics_path: "/actuator/prometheus"

  - job_name: "mall-security"
    static_configs:
      - targets: [ "127.0.0.1:18072" ]
    metrics_path: "/actuator/prometheus"

  - job_name: "mall-user"
    static_configs:
      - targets: [ "127.0.0.1:18073" ]
    metrics_path: "/actuator/prometheus"

  - job_name: "mall-id"
    static_configs:
      - targets: [ "127.0.0.1:18074" ]
    metrics_path: "/actuator/prometheus"

  ...
```

* `job_name`：模块名
* `targets`：目标地址，可以配置多个节点
* `metrics_path`：指标接口，模块添加 `Prometheus` 依赖后自带该接口

---

### 🌌 客户端

#### 添加依赖

```xml
<!-- Prometheus 监控 -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
    <version>1.6.12</version>
</dependency>
```

#### 添加配置

```yaml
# Actuator 服务健康监控配置
management:
  endpoints:
    web:
      exposure:
        # 开放所有 Actuator 端点
        include: "*"
  endpoint:
    health:
      # 显示详细信息
      show-details: always
    metrics:
      enabled: true
    prometheus:
      enabled: true
  metrics:
    tags:
      application: ${spring.appcation.name}
    export:
      prometheus:
        enabled: true
```
