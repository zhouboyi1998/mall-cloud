package com.cafe.security.service;

import com.cafe.security.model.bo.TokenDetails;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service
 * @Author: zhouboyi
 * @Date: 2022/7/8 9:47
 * @Description:
 */
public interface OauthService {

    /**
     * Oauth2 登录认证
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    TokenDetails token(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException;
}
