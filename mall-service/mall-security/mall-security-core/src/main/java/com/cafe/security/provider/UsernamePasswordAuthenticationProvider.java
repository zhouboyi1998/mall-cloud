package com.cafe.security.provider;

import com.cafe.security.service.UserDetailsExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.provider
 * @Author: zhouboyi
 * @Date: 2023/3/9 16:13
 * @Description: 用户名密码认证提供器
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    /**
     * 用户详细信息组装服务
     */
    private final UserDetailsExtensionService userDetailsExtensionService;

    /**
     * 密码编码器
     */
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthenticationProvider(
        UserDetailsExtensionService userDetailsExtensionService,
        PasswordEncoder passwordEncoder
    ) {
        this.userDetailsExtensionService = userDetailsExtensionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsExtensionService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // 用户名和密码匹配, 组装 AbstractAuthenticationToken 接口的实现类并返回
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            // 用户名和密码不匹配
            throw new BadCredentialsException("Username and password do not match!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
