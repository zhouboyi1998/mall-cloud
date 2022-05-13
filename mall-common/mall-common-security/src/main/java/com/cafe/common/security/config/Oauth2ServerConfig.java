package com.cafe.common.security.config;

import com.cafe.common.constant.AuthEnum;
import com.cafe.common.security.enhancer.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.security.config
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

    private AuthenticationManager authenticationManager;

    private JwtTokenEnhancer jwtTokenEnhancer;

    private UserDetailsService userDetailsService;

    private RsaCredentialConfig rsaCredentialConfig;

    @Autowired
    public Oauth2ServerConfig(
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JwtTokenEnhancer jwtTokenEnhancer,
        UserDetailsService userDetailsService,
        RsaCredentialConfig rsaCredentialConfig
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.userDetailsService = userDetailsService;
        this.rsaCredentialConfig = rsaCredentialConfig;
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
            new ClassPathResource(rsaCredentialConfig.getKeystore()),
            rsaCredentialConfig.getStorepass().toCharArray()
        );

        return keyStoreKeyFactory.getKeyPair(
            rsaCredentialConfig.getAlias(),
            rsaCredentialConfig.getStorepass().toCharArray()
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
            .withClient(AuthEnum.MANAGEMENT_CLIENT_ID.getValue())
            // 客户端密钥: 生成 RSA 证书文件 (jwt.jks) 时设置的密钥口令 (-keypass)
            .secret(passwordEncoder.encode(rsaCredentialConfig.getKeypass()))
            // 授权模式: password 密码模式, refresh_token 开启刷新令牌
            .authorizedGrantTypes("password", "refresh_token")
            // 授权范围
            .scopes("all")
            // 访问令牌 access_token 过期时间 (1小时)
            .accessTokenValiditySeconds(3600)
            // 刷新令牌 refresh_token 过期时间 (24小时)
            .refreshTokenValiditySeconds(86400);
    }

    /**
     * 令牌访问端点配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // List 存储令牌增强器
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
            .tokenEnhancer(tokenEnhancerChain);
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
        security.allowFormAuthenticationForClients();
    }
}
