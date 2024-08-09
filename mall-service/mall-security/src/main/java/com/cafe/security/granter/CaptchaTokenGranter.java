package com.cafe.security.granter;

import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.constant.security.GrantConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.granter
 * @Author: zhouboyi
 * @Date: 2023/2/25 20:28
 * @Description: 图片验证码授权器
 */
public class CaptchaTokenGranter extends AbstractTokenGranter {

    private final AuthenticationManager authenticationManager;

    private final RedisTemplate<String, Object> redisTemplate;

    public CaptchaTokenGranter(
        AuthenticationManager authenticationManager,
        AuthorizationServerTokenServices tokenServices,
        ClientDetailsService clientDetailsService,
        OAuth2RequestFactory requestFactory,
        RedisTemplate<String, Object> redisTemplate
    ) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GrantConstant.CAPTCHA, redisTemplate);
    }

    protected CaptchaTokenGranter(
        AuthenticationManager authenticationManager,
        AuthorizationServerTokenServices tokenServices,
        ClientDetailsService clientDetailsService,
        OAuth2RequestFactory requestFactory,
        String grantType,
        RedisTemplate<String, Object> redisTemplate
    ) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());

        // 验证码校验逻辑
        String key = RedisConstant.CAPTCHA_PREFIX + parameters.get(RequestConstant.Parameter.KEY);
        String code = parameters.get(RequestConstant.Parameter.CODE).toUpperCase();
        parameters.remove(RequestConstant.Parameter.KEY);
        parameters.remove(RequestConstant.Parameter.CODE);

        // 从 Redis 中获取正确的图片验证码文本
        String correctCode = (String) redisTemplate.opsForValue().get(key);
        // 校验输入的验证码是否正确
        if (Objects.equals(correctCode, code)) {
            redisTemplate.delete(key);
        } else if (Objects.isNull(correctCode)) {
            throw new InvalidGrantException("Captcha has expired!");
        } else {
            throw new InvalidGrantException("Captcha code input error!");
        }

        // 密码校验逻辑
        String username = parameters.get(RequestConstant.Parameter.USERNAME);
        String password = parameters.get(RequestConstant.Parameter.PASSWORD);
        parameters.remove(RequestConstant.Parameter.PASSWORD);
        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        try {
            userAuth = this.authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException e) {
            throw new InvalidGrantException(e.getMessage());
        }

        if (userAuth != null && userAuth.isAuthenticated()) {
            OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedOAuth2Request, userAuth);
        } else {
            throw new InvalidGrantException("Could not authenticate user: username -> " + username);
        }
    }
}
