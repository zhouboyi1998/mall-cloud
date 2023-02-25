<h2 align="center">📔 Spring Security</h2>

### 🧰 模块搭建

#### 使用 JDK 自带的 keytool 生成 RSA 证书文件

* 进入在 `JDK` 安装目录，在 `/bin` 目录下面执行以下命令

```bash
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
```

* 按提示设置密钥库密码、私钥密码等信息
* 最终会生成一个 `RSA` 证书文件 `jwt.jks`

---

### 📑 基础知识

#### OAuth2 中默认的 4 种授权模式

```
password (密码)
    |-- 用于使用密码登录的项目
    |-- 支持 refresh_token

authorization_code (授权码)
    |-- 用于第三方授权登录的项目
    |-- 支持 refresh_token

client_credential (客户端)
    |-- 用于后台的 API 消费者
    |-- 不支持 refresh_token

implicit (隐藏)
    |-- 用于 Web 浏览器
    |-- 不支持 refresh_token
```

#### Spring Security 中 4 种令牌存储方式

```
JdbcTokenStore (保存到数据库)
InMemoryTokenStore (保存到本地内存)
RedisTokenStore (保存到 Redis)
JwkTokenStore (全部信息返回到客户端)
```

---

### 🏹 实战

#### 刷新令牌是否复用

```
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.reuseRefreshTokens(false);
    }
}
```

* `endpoints.reuseRefreshTokens(true)`（复用，默认）
    * `Refresh Token` 不会刷新
    * 如果 `Refresh Token` 同时还设置成只能使用一次
    * 那么 `Access Token` 再次过期时就只能重新登录了

* `endpoints.reuseRefreshTokens(false)`（不复用）
    * 使用当前 `Refresh Token` 获取新的 `Access Token` 时
    * 同时获取新的 `Refresh Token`
    * 这样只要在 `Refresh Token` 有效期内不断刷新就可以永远不过期

#### 新增授权模式

* 新建一个类继承 `AbstractTokenGranter`
    * 重写继承的 `getOAuth2Authentication()` 方法
    * 在这个方法中编写校验 `Token` 的规则
* 在继承了 `AuthorizationServerConfigurerAdapter` 的配置类中
    * 修改令牌访问端点配置
    * 将自定义的授权模式加入到 `Spring Security` 授权模式列表中
* 最后修改 `application.yml` 配置文件
    * 开启自定义的授权模式
