<h2 align="center">📔 Nacos</h2>

### 📦 Windows 安装

#### Nacos 2.0.3

* 从 [**GitHub**](https://github.com/alibaba/nacos/releases) 下载 `2.0.3` 版本的 `Nacos`
* `nacos-server-2.0.3.zip`

---

### 🧰 服务端部署

#### 初始化数据库

* 进入解压后的 `/nacos/conf` 目录
* 执行 `nacos-mysql.sql`

#### 修改数据库连接配置

* 修改 `application.properties` 配置文件

```properties
# 数据库连接配置
db.url.0 = jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0 = root
db.password.0 = 123456

# 开启权限认证
nacos.core.auth.enabled = true
```

#### 运行 Nacos

* 进入 `/nacos/bin` 目录
* 运行 `startup.cmd` 批处理文件
* 访问 `Nacos` 控制台：`127.0.0.1:8848/nacos`
* 默认用户名 / 密码：`nacos` / `nacos`

#### 注意事项

* `Nacos` 在 `2.x` 版本有非常大的改动
* 导致 `Nacos Server 2.x` 可兼容 `Nacos Client 1.x`
* 但是 `Nacos Server 1.x` 不兼容 `Nacos Client 2.x`

#### 配置中心

* `/docs/nacos` 目录下存放的是需要 `Nacos` 配置中心管理的配置文件
* 导入配置文件到 `Nacos` 配置中心
    * 将该目录下的 `DEFAULT_GROUP` 目录压缩成 `.zip` 格式的压缩包
    * 导入配置时选择该压缩包上传
    * 压缩包中的目录名称 `DEFAULT_GROUP` 即配置文件导入到 `Nacos` 后的分组
