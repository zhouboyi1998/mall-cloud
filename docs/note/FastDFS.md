<h2 align="center">📔 FastDFS</h2>

### 🐳 Docker

* 拉取 `FastDFS` 镜像

```bash
docker pull morunchang/fastdfs
```

#### Tracker

* 运行 `Tracker` 容器

```bash
docker run -d --name tracker --net=host morunchang/fastdfs sh tracker.sh
```

* 设置 `Tracker` 容器随 `Docker` 启动

```bash
docker update --restart=always tracker
```

#### Storage

* 运行 `Storage` 容器
    * 将 `192.168.44.128` 替换成 `Tracker` 实际部署的 `IP`

```bash
docker run -d --name storage --net=host -e TRACKER_IP=192.168.44.128:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh
```

* 设置 `Storage` 容器随 `Docker` 启动

```bash
docker update --restart=always storage
```
