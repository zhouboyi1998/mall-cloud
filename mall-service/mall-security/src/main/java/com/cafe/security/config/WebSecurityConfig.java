package com.cafe.security.config;

import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.security.property.RSACredentialProperties;
import com.cafe.security.provider.EmailPasswordAuthenticationProvider;
import com.cafe.security.provider.MobilePasswordAuthenticationProvider;
import com.cafe.security.provider.UsernamePasswordAuthenticationProvider;
import com.cafe.security.service.UserDetailsExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:17
 * @Description: Web 安全配置类
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户详细信息组装服务
     */
    private final UserDetailsExtensionService userDetailsExtensionService;

    /**
     * RSA 证书配置
     */
    private final RSACredentialProperties rsaCredentialProperties;

    /**
     * 密码编码器 (SCrypt)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SCryptPasswordEncoder();
    }

    /**
     * 密钥对
     */
    @Bean
    public KeyPair keyPair() {
        // 获取密钥库
        ClassPathResource keyStore = new ClassPathResource(rsaCredentialProperties.getKeyStore());
        // 获取密钥库口令
        char[] storePass = rsaCredentialProperties.getStorePass().toCharArray();
        // 新建密钥库工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, storePass);
        // 使用密钥库口令从密钥库中获取密钥对
        return keyStoreKeyFactory.getKeyPair(rsaCredentialProperties.getAlias(), storePass);
    }

    /**
     * 用户名密码认证提供器
     */
    @Bean
    public UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider() {
        return new UsernamePasswordAuthenticationProvider(userDetailsExtensionService, passwordEncoder(), keyPair());
    }

    /**
     * 手机号密码认证提供器
     */
    @Bean
    public MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider() {
        return new MobilePasswordAuthenticationProvider(userDetailsExtensionService, passwordEncoder(), keyPair());
    }

    /**
     * 邮箱密码认证提供器
     */
    @Bean
    public EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider() {
        return new EmailPasswordAuthenticationProvider(userDetailsExtensionService, passwordEncoder(), keyPair());
    }

    /**
     * 认证管理器
     *
     * @throws Exception 认证管理器构建异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * HTTP 安全相关配置
     *
     * @param http HTTP 安全配置器
     * @throws Exception HTTP 安全配置异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .cors()
            .and()
            .authorizeRequests()
            .requestMatchers(EndpointRequest.toAnyEndpoint())
            .permitAll()
            // 允许直接访问自身的所有接口
            .antMatchers(new String[]{AuthorizationConstant.ALL_REQUEST})
            .permitAll()
            .anyRequest()
            .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            // 加载用户名密码认证提供器
            .authenticationProvider(usernamePasswordAuthenticationProvider())
            // 加载手机号密码认证提供器
            .authenticationProvider(mobilePasswordAuthenticationProvider())
            // 加载邮箱密码认证提供器
            .authenticationProvider(emailPasswordAuthenticationProvider())
            // 指定用户详细信息组装服务
            .userDetailsService(userDetailsExtensionService)
            // 指定密码编码器
            .passwordEncoder(passwordEncoder());
    }
}
