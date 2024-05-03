<h2 align="center">📔 Apollo</h2>

### 📦 模块搭建

* 从 [**GitHub**](https://github.com/apolloconfig/apollo/releases) 下载 `2.1.0` 版本的 `Apollo`
    * `apollo-configservice-2.1.0-github.zip`
    * `apollo-adminservice-2.1.0-github.zip`
    * `apollo-portal-2.1.0-github.zip`

---

### 🧰 服务端部署

#### 初始化数据库

* [**https://github.com/apolloconfig/apollo/blob/v2.1.0/scripts/sql/apolloconfigdb.sql**](https://github.com/apolloconfig/apollo/blob/v2.1.0/scripts/sql/apolloconfigdb.sql)
* [**https://github.com/apolloconfig/apollo/blob/v2.1.0/scripts/sql/apolloportaldb.sql**](https://github.com/apolloconfig/apollo/blob/v2.1.0/scripts/sql/apolloportaldb.sql)

#### 修改配置文件

* `\apollo-configservice-2.1.0-github\config\application-github.properties`

```properties
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/ApolloConfigDB?characterEncoding=utf8
spring.datasource.username = root
spring.datasource.password = 123456
```

* `\apollo-adminservice-2.1.0-github\config\application-github.properties`

```properties
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/ApolloConfigDB?characterEncoding=utf8
spring.datasource.username = root
spring.datasource.password = 123456
```

* `\apollo-portal-2.1.0-github\config\application-github.properties`

```properties
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/ApolloPortalDB?characterEncoding=utf8
spring.datasource.username = root
spring.datasource.password = 123456
```

---

### 🔑 脚本

* 启动顺序：`configservice` -> `adminservice` -> `portal`
* 关闭顺序相反

#### 启动脚本

```
@echo off
title Apollo 2.1.0

cd ./apollo-configservice-2.1.0-github/scripts
startup.sh

cd ../../apollo-adminservice-2.1.0-github/scripts
startup.sh

cd ../../apollo-portal-2.1.0-github/scripts
startup.sh

pause
```

#### 关闭脚本

```
@echo off
title Apollo 2.1.0

cd ./apollo-portal-2.1.0-github/scripts
shutdown.sh

cd ../../apollo-adminservice-2.1.0-github/scripts
shutdown.sh

cd ../../apollo-configservice-2.1.0-github/scripts
shutdown.sh

pause
```
