<h2 align="center">📔 ELKB</h2>

### 📦 Windows 安装

* 官网下载 `7.6.2` 版本的 `ElasticSearch`、`Logstash`、`Kibana`、`Filebeat` 压缩包
* 解压 `elasticsearch-7.6.2-windows-x86_64.zip`
* 解压 `logstash-7.6.2.zip`
* 解压 `kibana-7.6.2-windows-x86_64.zip`
* 解压 `filebeat-7.6.2-windows-x86_64.zip`

#### 批处理文件启动

###### ElasticSearch

* 直接使用 `/bin` 目录下的 `elasticsearch.bat` 启动

###### Logstash

* 新建 `.bat` 批处理文件，输入以下内容并保存

```bat
@echo off
title Logstash 7.6.2

cd ./logstash-7.6.2/bin
logstash -f ../config/logstash.conf

pause
```

###### Kibana

* 直接使用 `/bin` 目录下的 `kibana.bat` 启动

###### FileBeat

* 新建 `.bat` 批处理文件，输入以下内容并保存

```bat
@echo off
title FileBeat 7.6.2

cd ./filebeat-7.6.2-windows-x86_64
filebeat.exe -e -c filebeat.yml

pause
```

---

### 📒 ELKB 日志采集

#### 修改配置文件

###### `elasticsearch.yml`

```yaml
http:
  cors:
    enabled: true
    allow-origin: "*"
```

###### `logstash.yml`

```yaml
http.host: "127.0.0.1"
http.port: 9600-9700
```

###### `logstash.conf`

```conf
input {
  beats {
    port => 5044
  }
}

filter {
  json {
    source => "message"
  }

  mutate {
    rename => {
      "time" => "time"
      "description" => "description"
      "source" => "source"
      "url" => "url"
      "type" => "type"
      "clazz" => "class"
      "method" => "method"
      "argument" => "argument"
      "result" => "result"
      "throwable" => "throwable"
    }
  }
}

output {
  stdout { codec => rubydebug }
  elasticsearch {
    hosts => ["127.0.0.1:9200"]
    index => "mall-api-log-%{+YYYY.MM.dd}"
  }
}
```

###### `kibana.yml`

```yaml
i18n.locale: "zh-CN"
```

###### `filebeat.yml`

```yaml
filebeat.inputs:
  - type: log
    enabled: true
    paths:
      # - /var/log/*.log
      - d:\Project\mall\mall-cloud\logs\api\*.log

filebeat.config.modules:
  path: ${path.config}/modules.d/*.yml
  reload.enabled: true

setup.template.settings:
  index.number_of_shards: 1

setup.kibana:
  host: "127.0.0.1:5601"

output.logstash:
  hosts: [ "127.0.0.1:5044" ]

processors:
  - add_host_metadata: ~
  - add_cloud_metadata: ~
  - add_docker_metadata: ~
  - add_kubernetes_metadata: ~
```
