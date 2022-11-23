package com.cafe.gateway.authorization;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.HttpHeaderConstant;
import com.cafe.common.constant.HttpParameterConstant;
import com.cafe.common.constant.NumberConstant;
import com.cafe.common.constant.RedisConstant;
import com.cafe.common.constant.StringConstant;
import com.nimbusds.jose.JWSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.authorization
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:02
 * @Description: 授权管理器
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationManager.class);

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public AuthorizationManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取 Request
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 获取 Request Path 中的菜单路径
        String menuPath = request.getPath().subPath(NumberConstant.ZERO, NumberConstant.FOUR).toString();
        // 获取 Request Header 中的 Token
        String token = request.getHeaders().getFirst(HttpHeaderConstant.AUTHORIZATION_HEADER);
        // 移除 Token 中的令牌头, 获取 Access Token
        assert token != null;
        String accessToken = token.replace(HttpHeaderConstant.AUTHORIZATION_HEADER_PREFIX, StringConstant.EMPTY);
        try {
            // 解析 Access Token
            JWSObject jwsObject = JWSObject.parse(accessToken);
            // 从解析后的 Access Token 中获取用户详细信息
            String userDetails = jwsObject.getPayload().toString();
            // 获取用户详细信息中的用户id和客户端id
            Long userId = (Long) JSONUtil.parseObj(userDetails).get(HttpParameterConstant.USER_ID_PARAMETER);
            String clientId = (String) JSONUtil.parseObj(userDetails).get(HttpParameterConstant.CLIENT_ID_PARAMETER);
            LOGGER.info("AuthorizationManager.check(): userId -> {}, clientId -> {}, menuPath -> {}", userId, clientId, menuPath);
        } catch (ParseException e) {
            LOGGER.error("AuthorizationManager.check(): could not parse accessToken -> {}, message -> {}", accessToken, e.getMessage());
        }

        // 获取可以访问当前菜单的角色列表
        Object list = redisTemplate.opsForHash().get(RedisConstant.MENU_ROLE_MAP, menuPath);
        List<String> roleNameList = Convert.toList(String.class, list);

        // 当用户认证通过, 且用户的角色可以访问当前菜单, 当前用户可以访问当前请求
        return mono
            .filter(Authentication::isAuthenticated)
            .flatMapIterable(Authentication::getAuthorities)
            .map(GrantedAuthority::getAuthority)
            .any(roleNameList::contains)
            .map(AuthorizationDecision::new)
            .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
