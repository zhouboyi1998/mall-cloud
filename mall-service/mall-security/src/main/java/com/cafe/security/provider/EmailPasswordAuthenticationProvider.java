package com.cafe.security.provider;

import com.cafe.common.util.codec.RSAUtil;
import com.cafe.security.token.EmailPasswordAuthenticationToken;
import com.cafe.security.userdetails.MultiPrincipalUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.provider
 * @Author: zhouboyi
 * @Date: 2024/8/9 10:01
 * @Description: 邮箱密码认证提供器
 */
@RequiredArgsConstructor
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {

    /**
     * 用户详细信息加载服务
     */
    private final MultiPrincipalUserDetailsService multiPrincipalUserDetailsService;

    /**
     * 密码编码器
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * 密钥对
     */
    private final KeyPair keyPair;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        // 使用私钥解密获取原始密码
        String password = RSAUtil.decrypt(authentication.getCredentials().toString(), (RSAPrivateKey) keyPair.getPrivate());

        UserDetails userDetails = multiPrincipalUserDetailsService.loadUserByEmail(email);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // 邮箱和密码匹配, 组装 AbstractAuthenticationToken 接口的实现类并返回
            return new EmailPasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            // 邮箱和密码不匹配
            throw new BadCredentialsException("Email and password do not match!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
