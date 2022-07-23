<h2 align="center">📔 FastDFS</h2>

### 🐳 Docker

* 拉取 FastDFS 镜像

```bash
docker pull morunchang/fastdfs
```

* 运行 Tracker 容器

```bash
docker run -d --name tracker --net=host morunchang/fastdfs sh tracker.sh
```

* 运行 Storage 容器
    * 将 192.168.44.128 修改成 Tracker 宿主机的 IP

```bash
docker run -d --name storage --net=host -e TRACKER_IP=192.168.44.128:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh
```
