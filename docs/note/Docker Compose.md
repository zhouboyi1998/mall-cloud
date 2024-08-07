<h2 align="center">📔 Docker Compose</h2>

### 📦 Ubuntu 安装

* 选择与安装的 `Docker` 版本兼容的 `Docker Compose` 版本安装
    * 版本对应关系可以在 `Docker Compose` 的 `GitHub RELEASE` 页面查看

```shell
sudo curl -SL https://github.com/docker/compose/releases/download/v2.27.0/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
```

* 修改 `/usr/local/bin/docker-compose` 目录权限

```shell
sudo chmod +x /usr/local/bin/docker-compose
```

* 查看 `Docker Compose` 版本

```shell
docker-compose --version
```

* 锁定 `Docker Compose` 版本

```shell
sudo apt-mark hold docker-compose
```

---

### 🐳 Docker Compose 文件

```yaml
version: "3.9"
services:
  database:
    image: postgres
    ports:
      - "5432:5432"
    networks:
      - app-net
    restart: always
  app1:
    image: 192.168.159.128:5000/app/app1:latest
    ports:
      - "8080:8080"
    networks:
      - app-net
    volumes:
      - /var/app1/log:/log
    depends_on:
      - database
  app2:
    build:
      context: /usr/local/src/app
      dockerfile: Dockerfile
    ports:
      - "8081:8081"
    networks:
      - app-net
    volumes:
      - /var/app2/log:/log
    depends_on:
      - database
networks:
  app-net:
    driver: bridge
```

#### 配置项

* `version`：当前配置文件使用的语法版本


* `services`：服务列表
    * `{service_name}`：自定义的服务名称
        * `image`：启动容器所使用的镜像（默认使用本地镜像和 `latest` 版本）
        * `build`：使用 `Dockerfile` 文件构建镜像，再使用构建的镜像启动容器
            * `context`：指定 `Dockerfile` 文件所在的目录
            * `dockerfile`：指定 `Dockerfile` 文件名称（默认为 `Dockerfile`）
        * `ports`：端口映射列表
        * `networks`：容器所处的网桥列表
        * `volumes`：容器的挂载卷列表
        * `depends_on`：当前服务依赖的服务列表（依赖的服务全部启动后，才会启动当前服务）
        * `restart`：容器挂掉后的重启策略（`always` 总是重启容器；`no` 不重启容器）


* `networks`：网桥列表（处于同一个网桥下的服务才能互相通信）
    * `{network_name}`：自定义的网桥名称
        * `driver`：网桥模式（默认为 `bridge`）


* `volumes`：挂载卷列表
    * `{volume_name}`：`Docker` 中自定义的挂载卷名称（需要先使用 `Docker` 创建该挂载卷）

---

### 🔑 Docker Compose 命令

#### 命令参数

* `-f` / `--file`：指定 `Docker Compose` 文件（默认为当前目录下的 `docker-compose.yaml` 文件）

#### 通用参数

* `service_name`：指定需要操作的服务名称（默认为所有服务）

#### 命令

* 上线服务
    * `-d`：后台运行容器
    * `--build`：使用 `Dockerfile` 构建镜像，并使用构建的镜像启动容器

```shell
docker-compose [-f file_name] up [-d] [--build] [service_name]
```

* 下线服务（停止服务并删除服务）

```shell
docker-compose [-f file_name] down [service_name]
```

* 查看所有运行的服务

```shell
docker-compose ps
```

* 暂停服务

```shell
docker-compose pause [service_name]
```

* 恢复已暂停的服务

```shell
docker-compose unpause [service_name]
```

* 停止服务（不会删除服务，可以使用 `docker-compose start` 命令再次启动服务）

```shell
docker-compose stop [service_name]
```

* 启动已经存在的服务

```shell
docker-compose start [service_name]
```

* 重启服务
    * `-t`：指定服务延迟多长时间后重启（例如：`-t 10s` 表示服务在 `10` 秒后重启）

```shell
docker-compose restart [-t time] [service_name]
```

* 删除已经停止的服务
    * `-f` / `--force`：强制删除服务

```shell
docker-compose rm [-f] [service_name]
```

* 查看服务容器的日志
    * `-f`：持续查看日志

```shell
docker-compose logs [-f] [service_name]
```

* 查看服务容器内部运行的进程

```shell
docker-compose top [service_name]
```

---

### 🏹 项目实战

* 项目根目录下运行以下命令

#### 上线所有服务

```shell
docker-compose -f ./docs/docker-compose/docker-compose.yaml up -d
```

#### 下线所有服务

```shell
docker-compose -f ./docs/docker-compose/docker-compose.yaml down
```

#### 上线某个服务

* 将 `mall-id` 替换成实际需要上线的服务名称

```shell
docker-compose -f ./docs/docker-compose/docker-compose.yaml up -d mall-id
```

#### 下线某个服务

* 将 `mall-id` 替换成实际需要下线的服务名称

```shell
docker-compose -f ./docs/docker-compose/docker-compose.yaml down mall-id
```
