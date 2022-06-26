package com.cafe.security.manage.config;

import com.cafe.common.constant.RedisConstant;
import com.cafe.security.manage.enhancer.JwtTokenEnhancer;
import com.cafe.security.manage.property.ClientDetailsProperties;
import com.cafe.security.manage.property.RsaCredentialProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.config
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:13
 * @Description: OAuth2 认证服务配置
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 使用 SCrypt 加密
     */
    @Qualifier("sCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * 认证管理器
     */
    private AuthenticationManager authenticationManager;

    /**
     * 自定义令牌增强器
     */
    private JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * 用户详细信息加载类
     */
    private UserDetailsService userDetailsService;

    /**
     * RSA 证书配置
     */
    private RsaCredentialProperties rsaCredentialProperties;

    /**
     * 客户端详细信息配置
     */
    private ClientDetailsProperties clientDetailsProperties;

    /**
     * Redis 连接工厂
     */
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public Oauth2ServerConfig(
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JwtTokenEnhancer jwtTokenEnhancer,
        UserDetailsService userDetailsService,
        RsaCredentialProperties rsaCredentialProperties,
        ClientDetailsProperties clientDetailsProperties,
        RedisConnectionFactory redisConnectionFactory
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.userDetailsService = userDetailsService;
        this.rsaCredentialProperties = rsaCredentialProperties;
        this.clientDetailsProperties = clientDetailsProperties;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * 配置令牌存储方式 (如果不配置, 默认使用 JDBC 存储)
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // 使用 Redis 存储令牌
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix(RedisConstant.TOKEN_PREFIX);
        return redisTokenStore;
    }

    /**
     * 从 RSA 证书文件 (jwt.jks) 中获取密钥对
     *
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        // 使用 keystore 和 storepass 获取密钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
            new ClassPathResource(rsaCredentialProperties.getKeystore()),
            rsaCredentialProperties.getStorepass().toCharArray()
        );

        return keyStoreKeyFactory.getKeyPair(
            rsaCredentialProperties.getAlias(),
            rsaCredentialProperties.getStorepass().toCharArray()
        );
    }

    /**
     * 使用密钥对生成 OAuth2 JWT 访问令牌转换器
     *
     * @return
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     * 客户端详细信息配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            // 使用内存
            .inMemory()
            // 客户端id
            .withClient(clientDetailsProperties.getClientId())
            // 客户端密钥: 生成 RSA 证书文件 (jwt.jks) 时设置的密钥口令 (-keypass)
            .secret(passwordEncoder.encode(rsaCredentialProperties.getKeypass()))
            // 授权模式: password 密码模式, refresh_token 开启刷新令牌
            .authorizedGrantTypes(clientDetailsProperties.getAuthorizedGrantTypes())
            // 授权范围
            .scopes(clientDetailsProperties.getScopes())
            // 访问令牌 access_token 过期时间
            .accessTokenValiditySeconds(clientDetailsProperties.getAccessTokenValiditySeconds())
            // 刷新令牌 refresh_token 过期时间
            .refreshTokenValiditySeconds(clientDetailsProperties.getRefreshTokenValiditySeconds());
    }

    /**
     * 令牌访问端点配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 存储令牌增强器集合
        List<TokenEnhancer> tokenEnhancerList = new ArrayList<TokenEnhancer>();
        // 自定义 JWT 访问令牌转换器
        tokenEnhancerList.add(jwtTokenEnhancer);
        // OAuth2 JWT 访问令牌转换器
        tokenEnhancerList.add(jwtAccessTokenConverter());

        // 令牌增强器链
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // 加载 JWT 令牌增强器集合
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancerList);

        endpoints
            // 配置认证管理器
            .authenticationManager(authenticationManager)
            // 配置管理员账号详细信息加载类
            .userDetailsService(userDetailsService)
            // 配置访问令牌转换器, 使用 OAuth2 官方提供的
            .accessTokenConverter(jwtAccessTokenConverter())
            // 配置自定义的令牌增强器链
            .tokenEnhancer(tokenEnhancerChain)
            // 配置令牌存储方式: 使用 Redis 存储
            .tokenStore(tokenStore());
    }

    /**
     * 令牌安全约束配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许客户端的表单身份验证
        security
            .allowFormAuthenticationForClients();
    }
}
