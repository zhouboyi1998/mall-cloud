<h2 align="center">📔 GitLab Runner</h2>

### 📦 Ubuntu 安装

###### 更新软件包索引

```shell
sudo apt-get update
```

###### 更新软件包版本

```shell
sudo apt-get upgrade
```

###### 添加 `GitLab Runner` 仓库

```shell
sudo curl -L "https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh" | sudo bash
```

###### 安装 `GitLab Runner`

```shell
sudo apt-get install gitlab-runner
```

###### 查看 `GitLab Runner` 版本

```shell
gitlab-runner -v
```
