<h2 align="center">📔 Spring Security</h2>

### ⚓ 模块搭建

* 生成 RSA 证书文件

```
使用 JDK 自带的 keytool 生成
在 JDK /bin 目录下执行以下命令
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
按提示设置密码和其他信息
生成 RSA 证书文件 (jwt.jks)
```

### 📑 基础知识

* OAuth2 四种授权模式

```
password 密码模式
    |-- 用于使用密码登录的项目
    |-- 支持 refresh_token

authorization_code 授权码模式
    |-- 用于第三方授权登录的项目
    |-- 支持 refresh_token

client_credential 客户端凭证模式
    |-- 用于后台的 API 消费者
    |-- 不支持 refresh_token

implicit 隐藏模式
    |-- 用于 Web 浏览器
    |-- 不支持 refresh_token
```
