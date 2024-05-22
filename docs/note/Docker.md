<h2 align="center">📔 Docker</h2>

### 📦 Linux 安装

* 更新软件包索引

```shell
apt-get update
```

* 更新软件包版本

```shell
apt-get upgrade
```

* 安装 `HTTPS` 相关的软件包

```shell
apt-get install apt-transport-https ca-certificates curl software-properties-common
```

* 从 `Docker` 官网下载 `Docker APT Key`

```shell
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
```

* 添加 `Docker RELEASE` 仓库

```shell
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
```

* 再次更新软件包索引

```shell
apt-get update
```

* 安装 `Docker`

```shell
apt-get install docker-ce
```

* 运行 `Docker`

```shell
systemctl start docker
```

* 设置 `Docker` 开机启动

```shell
systemctl enable docker
```

* 查看 `Docker` 版本

```shell
docker version
```

* 运行 `Hello World` 容器

```shell
docker run hello-world
```

* 安装 `Docker CLI`

```shell
apt-get install docker-ce-cli
```

* 锁定 `Docker`、`Docker CLI`、`Containerd` 版本

```shell
apt-mark hold docker-ce docker-ce-cli containerd.io
```

---

### 🐳 Dockerfile 文件

* `FROM`：指定基础镜像
* `MAINTAINER`：镜像作者
* `LABEL`：添加镜像标签
* `ENV`：添加镜像内部的环境变量
* `ARG`：添加仅在镜像构建时使用的环境变量，镜像构建成功后不会保留
* `VOLUME`：添加挂载卷
* `WORKDIR`：指定工作目录
* `ADD`：新建文件
* `COPY`：复制文件到镜像中
* `USER`：指定执行后续命令时使用的用户或用户组
* `RUN`：编写在镜像构建时执行命令
* `EXPOSE`：指定需要暴露的端口
* `CMD`：编写容器启动后执行的命令
* `ENTRYPOINT`：编写容器启动后执行的命令
* `ONBUILD`：设置当此镜像作为基础镜像构建其它镜像时，需要执行的构建命令

---

### 🔑 Docker 命令

* 查看镜像列表

```shell
docker images
```

* 从镜像仓库拉取镜像

```shell
docker pull image_name[:image_tag]
```

* 删除镜像

```shell
docker rmi image_name[:image_tag]
```

* 使用镜像构建容器
    * `-d`：后台运行容器
    * `-p`：将容器的端口映射到宿主机的端口
    * `-v`：将主机的目录挂载到容器中
    * `-e`：设置环境变量
    * `-i`：以交互模式运行容器（通常与 `-t` 一起使用）
    * `-t`：为容器重新分配一个伪输入终端（通常与 `-i` 一起使用）
    * `--name`：指定容器名称
    * `--network`：设置容器的网络模式
    * `--restart`：设置容器退出后的重启策略

```shell
docker run [-d] [-p host_port:container_port] [--name container_name] image_name[:image_tag]
```

* 查看正在运行的容器

```shell
docker ps
```

* 查看所有容器

```shell
docker ps -a
```

* 启动容器

```shell
docker start container_name/container_id
```

* 重启容器

```shell
docker restart container_name/container_id
```

* 暂停容器

```shell
docker pause container_name/container_id
```

* 恢复已暂停的容器

```shell
docker unpause container_name/container_id
```

* 停止容器

```shell
docker stop container_name/container_id
```

* 强制停止容器

```shell
docker kill container_name/container_id
```

* 删除容器

```shell
docker rm container_name/container_id
```

* 查看容器的详细信息

```shell
docker inspect container_name/container_id
```

* 查看容器的日志

```shell
docker logs container_name/container_id
```

* 查看容器的端口映射

```shell
docker port container_name/container_id
```

* 查看容器内部运行的进程

```shell
docker top container_name/container_id
```

* 更新容器配置

```shell
docker update [options] container_name/container_id
```

* 进入容器命令行

```shell
docker exec -it [command] container_name/container_id
```

* 转入容器命令行

```shell
docker attach container_name/container_id
```

* 复制文件到容器中

```shell
docker cp [options] host_path container_name/container_id:container_path
```

* 使用容器构建镜像

```shell
docker commit [options] container_name/container_id image_name[:image_tag]
```

* 登录镜像仓库

```shell
docker login [options] repository_url
```

* 为镜像打上新的标签

```shell
docker tag image_name[:image_tag] repository_url/image_name[:image_tag]
```

* 推送镜像到镜像仓库

```shell
docker push repository_url/image_name[:image_tag]
```

* 查看 Docker 使用的磁盘空间

```shell
docker system df
```

* 释放 Docker 占用的磁盘空间

```shell
docker system prune
```

* 导出镜像到镜像归档文件中

```shell
docker save image_name[:image_tag] > file_name.tar
```

* 使用镜像归档文件导入镜像

```shell
docker load < file_name.tar
```

* 导出容器到容器快照文件中

```shell
docker export container_name/container_id > file_name.tar
```

* 使用容器快照文件导入镜像

```shell
docker import file_name.tar < image_name[:image_tag]
```

---

### 🏹 实战

#### 批量构建镜像

###### Windows

```bash
# 项目根目录下运行
.\docs\script\docker-build.bat
```

#### 批量运行容器

###### Windows

```bash
# 项目根目录下运行
.\docs\script\docker-run.bat
```
