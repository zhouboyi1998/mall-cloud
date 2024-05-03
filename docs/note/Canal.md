<h2 align="center">📔 Canal</h2>

### 📡 数据库监听

* `Binlog` 是一个二进制日志文件，记录 `MySQL` 数据库表的变更历史
* `MySQL` 使用该日志文件进行主从复制、增量备份、数据库还原


* 数据库监听需要启用 `Binlog`，开启 `MySQL` 主从模式

```mysql
-- 查看 MySQL 是否启用了 Binlog
SHOW VARIABLES LIKE 'log_bin';
-- 查看 Binlog 日志文件列表
SHOW BINARY LOGS;
-- 查看当前正在写入的 Binlog 日志文件
SHOW MASTER STATUS;
-- 清空重置 Binlog
RESET MASTER;
```

* `ON` 启用，`OFF` 禁用
* 如果没有启用，修改 `MySQL` 配置文件 `my.ini` / `my.cnf`
* 配置文件中添加以下三行

```ini
# 打开 Binlog
log_bin=mysql-bin

# 选择行模式
binlog-format=ROW

# 配置 MySQL server id, 不要和 Canal slave id 重复
server-id=1
```

#### Canal Server 配置

* `GitHub` 下载 `canal.deployer-1.1.5`
* `/conf/canal.properties` 配置文件中添加以下配置

```properties
# 数据库 URL
canal.instance.master.address = 127.0.0.1:3306
# 数据库 Username / Password
canal.instance.dbUsername = canal
canal.instance.dbPassword = canal
# 数据库编码集
canal.instance.connectionCharset = UTF-8
# 监听的 Database
canal.instance.defaultDatabaseName =
# 监听的 Table, 可以指定, 多个用逗号分割, 这里的正则表示监听所有
canal.instance.filter.regex = .*\\..*
```

* MySQL 创建一个专门给 Canal 使用的用户

```mysql
-- 创建 canal 用户, @'%' 远程登录用户, @'localhost' 本地登录用户 
CREATE USER 'canal'@'%' IDENTIFIED BY 'canal';

-- MySQL 5.7 授权, *.* 所有库、所有表
GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' IDENTIFIED BY 'canal';

-- MySQL 8.0 授权
GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' WITH GRANT OPTION;

-- 刷新使授权生效
FLUSH PRIVILEGES;

-- 如果密码设置没有生效, 使用 root 用户再次设置 canal 用户密码, 修改完成后再次刷新权限
ALTER USER 'canal'@'%' IDENTIFIED WITH mysql_native_password BY 'canal';
```

* 启动 `Canal Server`
    * Windows 环境 `/bin/startup.bat`
    * Linux 环境 `/bin/startup.sh`

---

### ❌ 错误处理

#### 错误 1

* 如果数据库发生变更，但是 `Canal Server` 没有响应
* 可以打开 `Canal Server` 安装目录
* 查看 `/logs/example/example.log` 日志文件


* 如果出现以下报错：

```
Could not find first log file name in binary log index file
```

* 报错原因：`Canal Server` 无法找到 `MySQL Binlog` 日志文件
* 解决方法：
    1. 关闭 `Canal Server`
    2. 删除安装目录下的 `/conf/example/meta.dat` 数据文件
    3. 重新启动 `Canal Server`
