package com.cafe.security.service;

import com.cafe.security.model.vo.Token;
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
     * @param principal  认证主体
     * @param parameters 认证参数
     * @return 认证令牌
     * @throws HttpRequestMethodNotSupportedException HTTP 请求方式不支持异常
     */
    Token token(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException;
}
