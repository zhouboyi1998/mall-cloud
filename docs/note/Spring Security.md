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

### 🏹 项目实战

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

`endpoints.reuseRefreshTokens(true)`

* 复用刷新令牌（默认设置）
* 在使用当前 `Refresh Token` 获取新的 `Access Token` 时，不获取新的 `Refresh Token`
* 如果同时设置了 `Refresh Token` 的使用次数，在次数用尽后用户就必须重新登录

`endpoints.reuseRefreshTokens(false)`

* 不复用刷新令牌
* 在使用当前 `Refresh Token` 获取新的 `Access Token` 时，同时获取新的 `Refresh Token`
* 这样在 `Refresh Token` 有效期内，用户可以一直获取新的 `Access Token`
* 只有用户长时间未登录，`Refresh Token` 过期，才需要重新登录

#### 新增授权模式

* 新建一个类继承 `AbstractTokenGranter`
* 重写继承的 `getOAuth2Authentication()` 方法
* 在这个方法中编写令牌授权的规则

```
public class CaptchaTokenGranter extends AbstractTokenGranter {
    ...
}
```

* 在 `AuthorizationServerConfigurerAdapter` 配置类中
* 修改令牌访问端点配置
* 将扩展的授权模式加入到 `Spring Security` 授权模式列表中

```
public CompositeTokenGranter compositeTokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
    ...
}
```

* 修改 `application.yml` 配置文件
* 开启新扩展的授权模式

```
client-config:
  client-details:
    - client-id: "manage"
      authorized-grant-types:
        - "captcha"
```

#### 新增认证令牌和认证提供器

* 如果新的授权模式需要使用对应的认证令牌，可以自定义认证令牌和认证提供器
* 新建一个类继承 `AbstractAuthenticationToken`

```
public class MobilePasswordAuthenticationToken extends AbstractAuthenticationToken {
    ...
}
```

* 新建一个类继承 `AuthenticationProvider`
* 重写继承来的 `authenticate()` 方法
* 在这个方法中编写令牌认证规则

```
public class MobilePasswordAuthenticationProvider implements AuthenticationProvider {
  ...
}
```

* 在 `WebSecurityConfigurerAdapter` 配置类中
* 将自定义的认证提供器添加到 `Spring` 容器中


* 注意：不可以使用 `@Autowired` 注入
    * 不然会有依赖构建顺序问题
    * 在 `WebSecurityConfigurerAdapter` 配置类加载时
    * 如果使用 `@Autowired` 注入认证提供器
    * 那么此时认证提供器就会开始实例化
    * 但此时密码编码器 `PasswordEncoded` 还没有实例化
    * 而认证提供器需要使用到密码编码器

```
@Bean
public MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider() {
    return new MobilePasswordAuthenticationProvider(userDetailsService, passwordEncoder());
}
```

#### 刷新令牌默认执行流程

* 如果使用了默认的刷新令牌授权器 `RefreshTokenGranter`
* 这个授权器会调用 `DefaultTokenServices.refreshAccessToken()` 方法
* 在这个方法中经过一系列调用
* 最终会调用 `UserDetailsService.loadUserByUsername()` 方法来获取用户信息
* 并创建一个新的 `Authentication` 对象


* 执行流程：

```
DefaultTokenServices.refreshAccessToken()
...
ProviderManager.authenticate()
...
UserDetailsService.loadUserByUsername()
```
