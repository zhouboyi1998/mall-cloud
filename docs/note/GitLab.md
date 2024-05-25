<h2 align="center">📔 GitLab</h2>

### 📦 Ubuntu 安装

###### 更新软件包索引

```shell
sudo apt-get update
```

###### 更新软件包版本

```shell
sudo apt-get upgrade
```

###### 安装 `GitLab` 依赖的软件包

```shell
sudo apt-get install curl openssh-server ca-certificates postfix
```

* 安装 `Postfix` 时会弹出一个配置窗口
* 选择 `Internet Site`，`OK`
* 检查系统主机名称是否正确，`OK`

###### 添加 `GitLab` 仓库

```shell
sudo curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.deb.sh | sudo bash
```

###### 安装 `GitLab`

```shell
sudo apt-get install gitlab-ce
```

###### 配置 `GitLab` 的外部访问地址

```shell
sudo vim /etc/gitlab/gitlab.rb
```

* 修改 `external_url` 配置

```
external_url '192.168.159.128:8000'
```

###### 停止 `GitLab`

```shell
sudo gitlab-ctl stop
```

###### 刷新 `GitLab` 配置

```shell
sudo gitlab-ctl reconfigure
```

###### 重启 `GitLab`

```shell
sudo gitlab-ctl restart
```

###### 启动 `GitLab`

```shell
sudo gitlab-ctl start
```

###### 查看 `GitLab` 状态

```shell
sudo gitlab-ctl status
```

###### 设置 `GitLab` 开机启动

```shell
sudo systemctl enable gitlab-runsvdir.service
```

###### 锁定 `GitLab` 版本

```shell
sudo apt-mark hold gitlab-ce
```

#### 解决访问 `GitLab` 报错 `502` 的问题

###### 查看 `GitLab` 日志

```shell
sudo gitlab-ctl tail
```

* 查看是否存在 `badgateway` 错误日志
* 该错误的原因是 `GitLab` 使用的 `Web Server` 默认使用 `8080` 端口
* `8080` 端口很容易和其它软件冲突

###### 修改 `GitLab` `Web Server` 使用的端口

```shell
sudo vim /etc/gitlab/gitlab.rb
```

* `GitLab 13.10` 及之前的版本使用的 `Web Server` 是 `Unicorn`
* 修改 `Unicorn` 的 `port` 配置

```
unicorn['port'] = 8101
```

* `GitLab 13.10` 之后的版本使用的 `Web Server` 是 `Puma`
* 修改 `Puma` 的 `port` 配置

```
puma['port'] = 8101
```

#### 修改 `root` 账号的登录密码

###### 查看 `root` 账号的临时密码

* `GitLab` 安装成功后会为 `root` 账号自动生成一个临时密码
* 保存在 `/etc/gitlab/initial_root_password` 文件中

```shell
sudo vim /etc/gitlab/initial_root_password
```

* 注意：安装成功 `24` 小时后该文件就会被删除
* 需要尽快查看密码并登录 `root` 账号修改密码


* 使用临时密码登录 `root` 账号
* 进入设置修改密码
