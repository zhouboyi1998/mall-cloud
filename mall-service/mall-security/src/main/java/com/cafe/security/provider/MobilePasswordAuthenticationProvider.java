package com.cafe.security.provider;

import com.cafe.common.util.codec.RSAUtil;
import com.cafe.security.service.UserDetailsExtensionService;
import com.cafe.security.token.MobilePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: 2023/3/10 11:00
 * @Description: 手机号密码认证提供器
 */
public class MobilePasswordAuthenticationProvider implements AuthenticationProvider {

    /**
     * 用户详细信息组装服务
     */
    private final UserDetailsExtensionService userDetailsExtensionService;

    /**
     * 密码编码器
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * 密钥对
     */
    private final KeyPair keyPair;

    @Autowired
    public MobilePasswordAuthenticationProvider(
        UserDetailsExtensionService userDetailsExtensionService,
        PasswordEncoder passwordEncoder,
        KeyPair keyPair
    ) {
        this.userDetailsExtensionService = userDetailsExtensionService;
        this.passwordEncoder = passwordEncoder;
        this.keyPair = keyPair;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobile = authentication.getName();
        // 使用私钥解密获取原始密码
        String password = RSAUtil.decrypt(authentication.getCredentials().toString(), (RSAPrivateKey) keyPair.getPrivate());

        UserDetails userDetails = userDetailsExtensionService.loadUserByMobile(mobile);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // 手机号和密码匹配, 组装 AbstractAuthenticationToken 接口的实现类并返回
            return new MobilePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            // 手机号和密码不匹配
            throw new BadCredentialsException("Mobile and password do not match!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobilePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
