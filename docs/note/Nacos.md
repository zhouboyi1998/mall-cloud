<h2 align="center">📔 Nacos</h2>

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
