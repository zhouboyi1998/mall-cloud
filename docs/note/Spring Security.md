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

#### 新增授权器

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
* 将扩展的授权器加入到 `Spring Security` 授权器列表中

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

* 如果新的授权器需要使用对应的认证令牌，可以自定义认证令牌和认证提供器
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

#### 默认刷新令牌授权器执行流程

* 如果使用了默认的刷新令牌授权器 `RefreshTokenGranter`
* 这个授权器会调用 `DefaultTokenServices.refreshAccessToken()` 方法来生成新的访问令牌
* 经过一系列调用，最终会调用 `UserDetailsService.loadUserByUsername()` 方法
* 并最终创建一个新的 `Authentication` 令牌

###### 使用刷新令牌生成新的访问令牌，调用链路中涉及的类和方法

```
// 令牌授权器
TokenGranter.grant()
AbstractTokenGranter.grant()
RefreshTokenGranter.getAccessToken()

// 令牌服务
AuthorizationServerTokenServices.refreshAccessToken()
DefaultTokenServices.refreshAccessToken()

// 认证管理器
AuthenticationManager.authenticate()

// 认证提供器
ProviderManager.authenticate()
AuthenticationProvider.authenticate()
PreAuthenticatedAuthenticationProvider.authenticate()

// 用户详情服务
AuthenticationUserDetailsService.loadUserDetails()
UserDetailsByNameServiceWrapper.loadUserDetails()
UserDetailsService.loadUserByUsername()
```

* 在 `PreAuthenticatedAuthenticationProvider.authenticate()` 方法中
* 将 `Authentication` 令牌转换成了具体子类 `PreAuthenticatedAuthenticationToken`
* 传递给 `UserDetailsByNameServiceWrapper.loadUserDetails()` 方法中使用
* 方法中调用了 `Principal.getName()` 方法
    * 由于令牌转换成了 `PreAuthenticatedAuthenticationToken` 类型
    * 使用的具体实现方法为 `AbstractAuthenticationToken.getName()`
* 使用其返回值作为 `username` 参数传递给 `UserDetailsService.loadUserByUsername()`
    * 由于选择了 `UserDetails` 作为 `Authentication` 令牌的 `principal`
    * 使用该方法实际调用的是 `UserDetails.getUsername()`
* 所以只要使用了默认的刷新令牌授权器
* 那么不管使用什么（用户名、手机号、邮箱）作为账号登录
* 都需要同时保存用户名到 `UserDetails.username` 中
* 除非重写一个自定义的刷新令牌授权器

#### 获取认证令牌

```
@PostMapping(value = "/token")
public ResponseEntity<Token> token(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
    Token token = oauthService.token(principal, parameters);
    return ResponseEntity.ok(token);
}
```

* `Spring Security` 框架会自动注入接口参数中的 `Principal` 对象
* 类似于 `Spring MVC` 框架会自动注入接口参数中的 `HttpServletRequest` 对象


* `parameters` 对象负责接收前端使用 `x-www-form-urlencoded` 方式传入的参数
