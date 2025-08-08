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

### 🧰 初始化用户

#### 用户

###### 创建项目用户 cafe

* **Username**：`cafe`
* **Password**：`cafe`

#### 权限

###### 将 Virtual host 根路径权限赋予给 cafe 用户

* **Virtual host**：`/`
* **Configure regexp**：`.*`
* **Write regexp**：`.*`
* **Read regexp**：`.*`

---

### 🏹 初始化队列

#### Binlog 数据库监听器相关

###### 交换机

* **Name**：`binlog`
* **Type**：`direct`
* **Durability**：`Durable`
* **Auto delete**：`No`
* **Internal**：`No`

###### 队列

* **Name**：`role-resource`
* **Durability**：`Durable`
* **Auto delete**：`No`

###### 路由键

* **Routing key**：`binlog-to-role-resource`

#### Canal 数据库监听器相关

###### 交换机

* **Name**：`canal`
* **Type**：`direct`
* **Durability**：`Durable`
* **Auto delete**：`No`
* **Internal**：`No`

###### 队列

* **Name**：`role-resource`
* **Durability**：`Durable`
* **Auto delete**：`No`

###### 路由键

* **Routing key**：`canal-to-role-resource`
