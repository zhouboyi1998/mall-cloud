<h2 align="center">📔 XXL-JOB</h2>

### 📦 Windows 安装

#### XXL-JOB 2.4.0

* 解压 `xxl-job-2.4.0.zip`
* 进入 `\doc\db` 目录
* 执行 `tables_xxl_job.sql`


* 进入 `\xxl-job-admin\src\main\resources` 目录
* 修改 `application.properties` 配置文件

```properties
# 控制台访问端口
server.port = 8001

# 数据库连接配置
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
spring.datasource.username = root
spring.datasource.password = 123456
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
```

* 根目录中打包

```bash
mvn clean package -DskipTests
```

* 进入 `\xxl-job-admin\target` 目录
* 运行

```bash
java -Xmx256m -XX:MaxPermSize=256m -jar xxl-job-admin-2.4.0.jar 
```

#### 批处理文件启动

* 新建 `.bat` 批处理文件，输入以下内容并保存

```bat
@echo off
title XXL-JOB 2.4.0

cd ./xxl-job-2.4.0/xxl-job-admin/target
java -Xmx256m -XX:MaxPermSize=256m -jar xxl-job-admin-2.4.0.jar 

pause
```
