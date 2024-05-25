<h2 align="center">📔 Harbor</h2>

### 📦 Ubuntu 安装

* 安装 `Harbor` 需要先安装 `Docker` 和 `Docker Compose`
* 从 [**GitHub**](https://github.com/goharbor/harbor/releases) 下载 `2.9.4` 版本的 `Harbor`
* 上传到 Linux 的 `/opt` 目录下
* 解压压缩包

```shell
sudo tar xf harbor-offline-installer-v2.9.4.tgz -C /opt
```

* 进入解压后的目录

```shell
cd /opt/harbor
```

* 复制 `harbor.yml.tmpl` 文件为 `harbor.yml`

```shell
sudo cp harbor.yml.tmpl harbor.yml
```

* 修改 `harbor.yml` 文件

```shell
sudo vim harbor.yml
```

* `hostname` 修改为虚拟机的实际 `IP` 地址
* `http` 端口从 `80` 修改为 `5000`
* 注释掉 `https` 相关的所有内容

```
# The IP address or hostname to access admin UI and registry service.
# DO NOT use localhost or 127.0.0.1, because Harbor needs to be accessed by external clients.
hostname: 192.168.159.128

# http related config
http:
  # port for http, default is 80. If https enabled, this port will redirect to https port
  port: 5000

# https related config
# https:
  # https port for harbor, default is 443
  # port: 443
  # The path of cert and key files for nginx
  # certificate: /your/certificate/path
  # private_key: /your/private/key/path
```

* 如果有需要，还可以修改 `Harbor` 控制台 `admin` 用户的密码

```
# The initial password of Harbor admin
# It only works in first time to install harbor
# Remember Change the admin password from UI after launching Harbor.
harbor_admin_password: Harbor12345
```

* 执行预处理脚本

```shell
sudo ./prepare
```

* 执行安装脚本

```shell
sudo ./install.sh
```

* 宿主机访问 `192.168.159.128:5000`
* 输入账号：`admin`
* 输入密码：`Harbor12345`

#### 重启 Harbor

* 进入 `Harbor` 安装目录

```shell
cd /opt/harbor
```

* 使用 `Docker Compose` 命令关闭 `Harbor`

```shell
sudo docker-compose down
```

* 启动 `Harbor`

```shell
sudo docker-compose up -d
```

---

### 🐳 Docker 推送镜像到 Harbor

#### 修改 Docker 守护进程配置

###### Linux

* 修改 `Docker` 的守护进程配置文件 `daemon.json`
    * 如果文件不存在，手动创建一个

```shell
sudo vim /etc/docker/daemon.json
```

* 在配置文件中添加以下内容

```json
{
  "insecure-registries": [
    "192.168.159.128:5000"
  ]
}
```

* 重新加载 `Docker` 守护进程配置

```shell
sudo systemctl daemon-reload
```

* 重启 `Docker`

```shell
sudo systemctl restart docker
```

###### Windows

* 打开 `Docker Desktop` 设置页面
* 选择` Docker Engine` 标签
* 在配置文件中添加以下内容

```json
{
  "insecure-registries": [
    "192.168.159.128:5000"
  ]
}
```

* 点击 `Apply&Restart`
* 如果使用 `Docker` 登录 `Harbor` 仍然失败
* 右击桌面右下角 `Docker Desktop` 图标
* 点击 `Restart` 再次重启

#### 推送

* 登录 `Harbor` 控制台
* 新增项目
    * 项目名称：`mall`
    * 访问级别：`私有`
    * 项目配额限制：`-1`
    * 镜像代理：`否`

* 使用 `Docker` 登录 `Harbor`

```shell
docker login 192.168.159.128:5000
```

* 按提示输入 `Harbor` 的用户名和密码

* 标记本地镜像

```shell
docker tag mall-id:latest 192.168.159.128:5000/mall/mall-id:latest
```

* 推送本地镜像到 `Harbor`

```shell
docker push 192.168.159.128:5000/mall/mall-id:latest
```
