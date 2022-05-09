package com.cafe.security.management.config;

import com.cafe.common.security.token.CustomizeJwtAccessTokenConverter;
import com.cafe.security.management.service.impl.AdminDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.config
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:13
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private CustomizeJwtAccessTokenConverter customizeJwtAccessTokenConverter;
    private AdminDetailsServiceImpl adminDetailsServiceImpl;

    @Autowired
    public AuthenticationServerConfig(
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        CustomizeJwtAccessTokenConverter customizeJwtAccessTokenConverter,
        AdminDetailsServiceImpl adminDetailsServiceImpl
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.customizeJwtAccessTokenConverter = customizeJwtAccessTokenConverter;
        this.adminDetailsServiceImpl = adminDetailsServiceImpl;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
    }
}
