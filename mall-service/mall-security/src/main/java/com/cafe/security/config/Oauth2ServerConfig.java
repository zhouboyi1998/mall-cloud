package com.cafe.security.config;

import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.security.enhancer.JwtTokenEnhancer;
import com.cafe.security.granter.CaptchaTokenGranter;
import com.cafe.security.granter.EmailTokenGranter;
import com.cafe.security.granter.MobileTokenGranter;
import com.cafe.security.property.ClientProperties;
import com.cafe.security.property.ClientProperties.Detail;
import com.cafe.security.property.RSACredentialProperties;
import com.cafe.security.service.UserDetailsExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.config
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:13
 * @Description: OAuth2 认证服务配置类
 */
@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 密码编码器
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * 密钥对
     */
    private final KeyPair keyPair;

    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 自定义令牌增强器
     */
    private final JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * 用户详细信息组装服务
     */
    private final UserDetailsExtensionService userDetailsExtensionService;

    /**
     * RSA 证书配置
     */
    private final RSACredentialProperties rsaCredentialProperties;

    /**
     * 客户端配置
     */
    private final ClientProperties clientProperties;

    /**
     * Redis 连接工厂
     */
    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * Redis 交互模板
     */
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 令牌存储仓库
     */
    @Bean
    public TokenStore tokenStore() {
        // 使用 Redis 存储令牌 (如果不配置, 默认使用 JDBC 存储)
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix(RedisConstant.TOKEN_PREFIX);
        return redisTokenStore;
    }

    /**
     * OAuth2 JWT 访问令牌转换器
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 使用密钥对生成 OAuth2 JWT 访问令牌转换器
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    /**
     * 令牌增强器链
     */
    public TokenEnhancerChain tokenEnhancerChain() {
        // 新建存储令牌增强器的集合
        List<TokenEnhancer> tokenEnhancerList = new ArrayList<>();
        // 添加自定义 JWT 令牌增强器
        tokenEnhancerList.add(jwtTokenEnhancer);
        // 添加密钥对生成的 OAuth2 JWT 访问令牌转换器
        tokenEnhancerList.add(jwtAccessTokenConverter());

        // 新建令牌增强器链
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // 加载令牌增强器集合
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancerList);
        return tokenEnhancerChain;
    }

    /**
     * 令牌授权器
     *
     * @param endpoints 认证服务端点配置器
     */
    public CompositeTokenGranter compositeTokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        // 获取 Oauth2 默认提供的授权器列表
        List<TokenGranter> tokenGranterList = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));

        // 初始化图片验证码授权器
        CaptchaTokenGranter captchaTokenGranter = new CaptchaTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(), redisTemplate);
        // 初始化手机号授权器
        MobileTokenGranter mobileTokenGranter = new MobileTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        // 初始化邮箱授权器
        EmailTokenGranter emailTokenGranter = new EmailTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());

        // 添加自定义扩展的授权器到授权其列表中
        tokenGranterList.add(captchaTokenGranter);
        tokenGranterList.add(mobileTokenGranter);
        tokenGranterList.add(emailTokenGranter);

        // 新建复合授权器, 加载授权器集合
        return new CompositeTokenGranter(tokenGranterList);
    }

    /**
     * 客户端详细信息配置
     *
     * @param clients 客户端详细信息配置器
     */
    @SneakyThrows
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) {
        // 获取所有客户端详细信息配置
        List<Detail> clientDetails = clientProperties.getDetails();
        // 使用内存
        clients.inMemory();
        // 配置所有客户端的详细信息
        for (Detail clientDetail : clientDetails) {
            clients.and()
                // 客户端id
                .withClient(clientDetail.getClientId())
                // 客户端密钥: 生成 RSA 证书文件 (jwt.jks) 时设置的密钥口令 (key-pass)
                .secret(passwordEncoder.encode(rsaCredentialProperties.getKeyPass()))
                // 允许的授权模式
                .authorizedGrantTypes(clientDetail.getAuthorizedGrantTypes())
                // 授权范围
                .scopes(clientDetail.getScopes())
                // 访问令牌 access_token 过期时间
                .accessTokenValiditySeconds(clientDetail.getAccessTokenValiditySeconds())
                // 刷新令牌 refresh_token 过期时间
                .refreshTokenValiditySeconds(clientDetail.getRefreshTokenValiditySeconds());
        }
    }

    /**
     * 令牌访问端点配置
     *
     * @param endpoints 认证服务端点配置器
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            // 认证管理器
            .authenticationManager(authenticationManager)
            // 不复用刷新令牌
            .reuseRefreshTokens(false)
            // 用户详细信息组装服务
            .userDetailsService(userDetailsExtensionService)
            // 登录请求限制的 HTTP 类型
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
            // 访问令牌转换器
            .accessTokenConverter(jwtAccessTokenConverter())
            // 令牌增强器链
            .tokenEnhancer(tokenEnhancerChain())
            // 令牌授权器
            .tokenGranter(compositeTokenGranter(endpoints))
            // 令牌存储方式: 使用 Redis 存储
            .tokenStore(tokenStore());
    }

    /**
     * 令牌安全约束配置
     *
     * @param security 令牌安全约束配置器
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 允许客户端的表单身份验证
        security.allowFormAuthenticationForClients();
    }
}
