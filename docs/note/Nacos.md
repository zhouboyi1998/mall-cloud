<h2 align="center">📔 Nacos</h2>

### 📦 Windows 安装

#### Nacos 2.0.3

* 解压 `nacos-server-2.0.3.zip`
* 进入 `\nacos\conf` 目录
* 执行 `nacos-mysql.sql`


* 修改 `application.properties` 配置文件

```properties
# 数据库连接配置
db.url.0 = jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0 = root
db.password.0 = 123456

# 开启权限认证
nacos.core.auth.enabled = true
```

* 运行 `\nacos\bin\startup.cmd` 批处理文件
* 访问 `Nacos` 控制台：`127.0.0.1:8848/nacos`
* 默认用户名 / 密码：`nacos` / `nacos`

---

### 🧰 模块搭建

* `Nacos` 在 `2.x` 版本有非常大的改动
* 导致 `Nacos Server 2.x` 可兼容 `Nacos Client 1.x`
* 但是 `Nacos Server 1.x` 不兼容 `Nacos Client 2.x`


* 项目使用的 `Spring Cloud Alibaba` 版本是 `2.2.9.RELEASE`
* 其中依赖的 `Nacos Client` 版本是 `2.1.0`
* 所以需要搭配使用 `2.x` 版本的 `Nacos Server`

#### 配置中心

* `/docs/nacos/DEFAULT_GROUP` 目录下存放的是需要 `Nacos` 配置中心管理的配置文件
* 将该目录压缩成 `.zip` 格式的压缩包并上传到 `Nacos`


* 注意：压缩包中的文件夹名称即配置文件导入到 `Nacos` 后的分组
* 所以压缩包名称可以随意更改，但是压缩包中的文件夹名称不可以随意更改
