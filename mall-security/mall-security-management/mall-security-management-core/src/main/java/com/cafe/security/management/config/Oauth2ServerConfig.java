package com.cafe.security.management.config;

import com.cafe.security.management.constant.AuthEnum;
import com.cafe.security.management.enhancer.JwtTokenEnhancer;
import com.cafe.security.management.service.impl.AdminDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
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
 * @Package: com.cafe.security.management.config
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:13
 * @Description: OAuth2 认证服务相关配置
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenEnhancer jwtTokenEnhancer;
    private AdminDetailsServiceImpl adminDetailsServiceImpl;

    @Autowired
    public Oauth2ServerConfig(
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JwtTokenEnhancer jwtTokenEnhancer,
        AdminDetailsServiceImpl adminDetailsServiceImpl
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.adminDetailsServiceImpl = adminDetailsServiceImpl;
    }

    /**
     * 从 RSA证书文件(jwt.jks) 中获取密钥对
     *
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
            new ClassPathResource("jwt.jks"), "123456".toCharArray()
        );
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
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
            // 客户端密钥: 生成 RSA证书文件(jwt.jks) 时设置的密钥
            .secret(new SCryptPasswordEncoder().encode("123456"))
            // 授权模式: password 密码模式, refresh_token 开启刷新令牌
            .authorizedGrantTypes("password", "refresh_token")
            // 授权范围
            .scopes("all")
            // 访问令牌 access_token 过期时间
            .accessTokenValiditySeconds(3600)
            // 刷新令牌 refresh_token 过期时间
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
            .userDetailsService(adminDetailsServiceImpl)
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
