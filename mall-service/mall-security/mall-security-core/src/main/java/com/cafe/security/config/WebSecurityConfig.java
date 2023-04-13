package com.cafe.security.config;

import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.security.provider.MobilePasswordAuthenticationProvider;
import com.cafe.security.provider.UsernamePasswordAuthenticationProvider;
import com.cafe.security.service.UserDetailsExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:17
 * @Description: SpringSecurity Web 配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户详细信息组装服务
     */
    private final UserDetailsExtensionService userDetailsExtensionService;

    @Autowired
    public WebSecurityConfig(UserDetailsExtensionService userDetailsExtensionService) {
        this.userDetailsExtensionService = userDetailsExtensionService;
    }

    /**
     * 密码编码器 (SCrypt)
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SCryptPasswordEncoder();
    }

    /**
     * 用户名密码认证提供器
     *
     * @return
     */
    @Bean
    public UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider() {
        return new UsernamePasswordAuthenticationProvider(userDetailsExtensionService, passwordEncoder());
    }

    /**
     * 手机号密码认证提供器
     *
     * @return
     */
    @Bean
    public MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider() {
        return new MobilePasswordAuthenticationProvider(userDetailsExtensionService, passwordEncoder());
    }

    /**
     * 认证管理器
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * HTTP 相关配置
     *
     * @param http
     * @throws Exception
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
            // 指定用户详细信息组装服务
            .userDetailsService(userDetailsExtensionService)
            // 指定密码编码器
            .passwordEncoder(passwordEncoder());
    }
}
