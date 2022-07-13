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
* 查看 `MySQL` 是否启用了 `Binlog`

```sql
show variables like 'log_bin';
```

* `ON` 表示启用，`OFF` 表示未启用
* 如果没有启用，修改 `MySQL` 配置文件 `my.ini` / `my.cnf`
* 配置文件中添加以下三行

```ini
log_bin=mysql-bin
binlog-format=ROW
server-id=1
```

#### Canal 数据库监听

* `GitHub` 下载 `canal.deployer-1.1.5`
* `/conf/canal.properties` 配置文件中添加以下配置

```properties
# 数据库 URL
canal.instance.master.address=127.0.0.1:3306
# 数据库 Username / Password
canal.instance.dbUsername=canal
canal.instance.dbPassword=canal
canal.instance.connectionCharset = UTF-8

# 监听的 Database
canal.instance.defaultDatabaseName=
# 监听的 Table, 可以指定, 多个用逗号分割, 这里的正则表示监听所有
canal.instance.filter.regex=.*\\..*
```

* Windows 环境使用 `/bin/startup.bat` 启动 Canal Server
