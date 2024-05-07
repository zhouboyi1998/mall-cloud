<h2 align="center">📔 RabbitMQ</h2>

### 📦 Windows 安装

#### Erlang 18.1

###### 安装

* 运行安装程序 `erlang_otp_win64_18.1.exe`
* 除了安装目录，其它选项保持默认

###### 添加环境变量

* 系统变量
    * 变量名：`ERLANG_HOME`
    * 变量值：`Erlang` 安装目录
* 系统变量 `Path`
    * `%ERLANG_HOME%\bin`

#### RabbitMQ 3.6.5

###### 安装

* 运行安装程序 `rabbitmq-server-3.6.5.exe`
* 除了安装目录，其它选项保持默认

###### 添加环境变量

* 系统变量
    * 变量名：`RABBITMQ_HOME`
    * 变量值：`RabbitMQ` 安装目录
* 系统变量 `Path`
    * `%RABBITMQ_HOME%\sbin`

###### 开启 Web 管理界面插件

* 进入 `cmd`
* 开启 `Web` 管理界面插件：`rabbitmq-plugins enable rabbitmq_management`
* 重启 `RabbitMQ` 服务：`net stop RabbitMQ && net start RabbitMQ`
* 访问 `Web` 管理界面：`127.0.0.1:15672`
* 默认用户名 / 密码：`guest` / `guest`

---

### 🧰 初始化环境

* 进入 `RabbitMQ` 管理界面，创建项目需要使用的**用户、交换机、队列、路由键**

#### Admin

###### 创建 binlog 用户

* **Username**：`binlog`
* **Password**：`binlog`

###### 创建 canal 用户

* **Username**：`canal`
* **Password**：`canal`

#### Permissions

* 分别进入 `binlog`、`canal` 用户的设置界面，添加 `Permissions`

###### 分别添加 / Virtual host 给 binlog、canal 用户

* **Virtual host**：`/`
* **Configure regexp**：`.*`
* **Write regexp**：`.*`
* **Read regexp**：`.*`

#### Exchange

###### 创建 binlog 交换机

* **Name**：`binlog`
* **Type**：`direct`
* **Durability**：`Durable`
* **Auto delete**：`No`
* **Internal**：`No`

###### 创建 canal 交换机

* **Name**：`canal`
* **Type**：`direct`
* **Durability**：`Durable`
* **Auto delete**：`No`
* **Internal**：`No`

#### Queue

###### 创建 role-menu 队列

* **Name**：`role-menu`
* **Durability**：`Durable`
* **Auto delete**：`No`

#### Routing Key

* 进入 `role-menu` 队列的设置界面，添加 `Bindings`

###### 绑定 binlog 交换机和 role-menu 队列

* **From exchange**：`binlog`
* **Routing key**：`binlog-to-role-menu`

###### 绑定 canal 交换机和 role-menu 队列

* **From exchange**：`canal`
* **Routing key**：`canal-to-role-menu`
