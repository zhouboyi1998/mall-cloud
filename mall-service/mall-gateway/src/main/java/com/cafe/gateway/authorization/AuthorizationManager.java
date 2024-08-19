package com.cafe.gateway.authorization;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.core.request.UserDetails;
import com.cafe.common.util.json.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nimbusds.jose.JWSObject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.authorization
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:02
 * @Description: 授权管理器
 */
@RequiredArgsConstructor
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationManager.class);

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取 Request
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 获取 Request Path 中的菜单路径
        String menuPath = request.getPath().subPath(IntegerConstant.ZERO, IntegerConstant.FOUR).toString();
        // 获取 Request Header 中的 Token
        String token = Optional.ofNullable(request.getHeaders().getFirst(RequestConstant.Header.AUTHORIZATION))
            .orElseThrow(NullPointerException::new);
        // 移除 Token 中的令牌头, 获取 Access Token
        String accessToken = token.replace(RequestConstant.Header.BEARER_PREFIX, StringConstant.EMPTY);
        try {
            // 解析 Access Token
            JWSObject jwsObject = JWSObject.parse(accessToken);
            // 从解析后的 Access Token 中获取载荷
            String payload = jwsObject.getPayload().toString();
            // 解析载荷获取用户详细信息
            UserDetails userDetails = JacksonUtil.readValue(payload, UserDetails.class);
            LOGGER.info("AuthorizationManager.check(): user id -> {}, client id -> {}, menu path -> {}", userDetails.getUserId(), userDetails.getClientId(), menuPath);
        } catch (ParseException e) {
            LOGGER.error("AuthorizationManager.check(): Could not parse token! access token -> {}, message -> {}", accessToken, e.getMessage(), e);
        }

        // 获取可以访问当前菜单的角色列表
        List<String> roleNameList = JacksonUtil.convertValue(redisTemplate.opsForHash().get(RedisConstant.MENU_ROLE_MAP, menuPath), new TypeReference<List<String>>() {});

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
