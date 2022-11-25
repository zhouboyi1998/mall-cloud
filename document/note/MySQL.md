<h2 align="center">📔 MySQL</h2>

### 📝 SQL 规范

1. 禁止使用 `SELECT *`，需要哪些字段必须明确指出
    * 增加查询分析器成本
    * 增减字段容易与 `resultMap` 配置不一致
    * 无用字段增加网络消耗，尤其是 `text` 类型的字段


2. 避免使用 `IS NULL` / `IS NOT NULL` 关键字
    * 如果需要判空：使用 `ISNULL()` / `!ISNULL()` 函数


3. 禁止使用 `NULL` 关键字来判断是否等于 `NULL` 值
    * 如果需要判断是否等于 `NULL` 值：使用 `ISNULL()` 函数
    * `NULL = NULL` 的返回结果是 `NULL` 而不是 `true`
    * `NULL <> NULL` 的返回结果是 `NULL` 而不是 `false`
    * `NULL <> 1` 的返回结果是 `NULL` 而不是 `true`


4. 禁止使用级联更新和外键
    * 级联更新是强阻塞，存在数据库更新风暴的风险
    * 如果使用外键：
        * `tb_student` 中的 `student_id` 是主键
        * `tb_score` 中的 `student_id` 是外键
        * 如果更新 `tb_student` 中的 `student_id`
        * 会同时触发 `tb_score` 中的 `student_id` 更新
        * 即触发了级联更新
    * 外键影响数据库的插入速度


5. 禁止使用 `COUNT(列名)` 代替 `COUNT(*)`
    * `COUNT(列名)` 不会统计该列中为空的行

---

### 🩺 数据库监听

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


* 如果 `Canal` 停止工作
* 可以查看安装目录下的 `/logs/example/example.log` 日志文件
* 查看日志文件中的报错信息，如果出现以下报错：

```
Could not find first log file name in binary log index file
```

* 报错原因：`Canal` 无法找到 `Binlog` 文件
* 解决方法：
    * 停止 `Canal`
    * 删除安装目录下的 `/conf/example/meta.dat`
    * 重启 `Canal`
