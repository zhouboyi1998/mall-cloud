package com.cafe.security.granter;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.constant.security.GrantConstant;
import com.cafe.security.token.EmailPasswordAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.granter
 * @Author: zhouboyi
 * @Date: 2024/8/9 9:46
 * @Description: 邮箱授权器
 */
public class EmailTokenGranter extends AbstractTokenGranter {

    private final AuthenticationManager authenticationManager;

    public EmailTokenGranter(
        AuthenticationManager authenticationManager,
        AuthorizationServerTokenServices tokenServices,
        ClientDetailsService clientDetailsService,
        OAuth2RequestFactory requestFactory
    ) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GrantConstant.EMAIL);
    }

    protected EmailTokenGranter(
        AuthenticationManager authenticationManager,
        AuthorizationServerTokenServices tokenServices,
        ClientDetailsService clientDetailsService,
        OAuth2RequestFactory requestFactory,
        String grantType
    ) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());

        // 邮箱密码校验逻辑
        String email = parameters.get(RequestConstant.Parameter.EMAIL);
        String password = parameters.get(RequestConstant.Parameter.PASSWORD);
        parameters.remove(RequestConstant.Parameter.PASSWORD);
        Authentication userAuth = new EmailPasswordAuthenticationToken(email, password);
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
            throw new InvalidGrantException("Could not authenticate user: email -> " + email);
        }
    }
}
