<h2 align="center">📔 MySQL</h2>

### 📝 SQL 规范

* 禁止使用 `SELECT *`，需要哪些字段必须明确指出
    1. 增加查询分析器成本
    2. 增减字段容易与 `resultMap` 配置不一致
    3. 无用字段增加网络消耗，尤其是 `text` 类型的字段

---

* 避免使用 `IS NULL` / `IS NOT NULL` 关键字
* 如果需要判空：使用 `ISNULL()` / `!ISNULL()` 函数


* 禁止使用 `NULL` 关键字来判断是否等于 `NULL` 值
* 如果需要判断是否等于 `NULL` 值：使用 `ISNULL()` 函数
    1. `NULL = NULL` 的返回结果是 `NULL` 而不是 `true`
    2. `NULL <> NULL` 的返回结果是 `NULL` 而不是 `false`
    3. `NULL <> 1` 的返回结果是 `NULL` 而不是 `true`

---

* 禁止使用级联更新和外键
    1. 级联更新是强阻塞，存在数据库更新风暴的风险
    2. 如果使用外键：
        * `tb_student` 中的 `student_id` 是主键
        * `tb_score` 中的 `student_id` 是外键
        * 如果更新 `tb_student` 中的 `student_id`
        * 会同时触发 `tb_score` 中的 `student_id` 更新
        * 即触发了级联更新
    3. 外键影响数据库的插入速度

---

* 禁止使用 `COUNT(列名)` 代替 `COUNT(*)`
* `COUNT(列名)` 不会统计该列中为空的行

---

### 🩺 数据库监听

* `Binlog` 是一个二进制日志文件，记录 `MySQL` 数据库表的变更历史
* `MySQL` 使用该日志文件进行主从复制、增量备份、数据库还原

---

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

* `ON` 表示启用，`OFF` 表示未启用
* 如果没有启用，修改 `MySQL` 配置文件 `my.ini` / `my.cnf`
* 配置文件中添加以下三行

```ini
# 打开 Binlog
log_bin=mysql-bin

# 选择 ROW(行) 模式
binlog-format=ROW

# 配置 MySQL server_id, 不要和 Canal slave_id 重复
server-id=1
```

#### Canal Server 配置

* `GitHub` 下载 `canal.deployer-1.1.5`
* `/conf/canal.properties` 配置文件中添加以下配置

```properties
# 数据库 URL
canal.instance.master.address=127.0.0.1:3306
# 数据库 Username / Password
canal.instance.dbUsername=canal
canal.instance.dbPassword=canal
# 数据库编码集
canal.instance.connectionCharset=UTF-8
# 监听的 Database
canal.instance.defaultDatabaseName=
# 监听的 Table, 可以指定, 多个用逗号分割, 这里的正则表示监听所有
canal.instance.filter.regex=.*\\..*
```

* MySQL 创建一个专门给 Canal 使用的用户

```mysql
-- 创建 canal 用户, @'%' 表示远程登录用户, @'localhost' 表示本地登录用户 
CREATE USER 'canal'@'%' IDENTIFIED BY 'canal';
-- MySQL 5.7 授权, *.* 表示所有库、所有表
GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' IDENTIFIED BY 'canal';
-- MySQL 8.0 授权
GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' WITH GRANT OPTION;
-- 刷新使授权生效
FLUSH PRIVILEGES;

-- 如果密码设置没有生效, 使用 root 用户再次设置 canal 用户密码, 修改完成后再次刷新权限
ALTER USER 'canal'@'%' IDENTIFIED WITH mysql_native_password BY 'canal';
```

* Windows 环境使用 `/bin/startup.bat` 启动 Canal Server
* Linux 环境使用 `/bin/startup.sh` 启动 Canal Server
