package com.cafe.common.security.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.security.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:17
 * @Description: SpringSecurity Web 配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HTTP 相关配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
            // 允许直接访问 /keyPair/rsa/publicKey 接口, 获取 RSA 公钥信息
            .antMatchers("/keyPair/rsa/publicKey").permitAll()
            .anyRequest()
            .authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 使用 SCrypt 加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SCryptPasswordEncoder();
    }
}
