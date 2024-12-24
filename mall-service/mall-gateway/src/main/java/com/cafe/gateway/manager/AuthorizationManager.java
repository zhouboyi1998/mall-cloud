package com.cafe.gateway.manager;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.manager
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:02
 * @Description: 授权管理器
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取 Request
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 获取菜单路径
        String menuPath = request.getPath().subPath(IntegerConstant.ZERO, IntegerConstant.FOUR).toString();
        // 根据菜单路径, 获取可以访问当前菜单的角色列表
        List<String> roleNameList = JacksonUtil.convertValue(redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLE_MAP, menuPath), new TypeReference<List<String>>() {});
        log.info("AuthorizationManager.check(): menu path -> {}, role name list -> {}", menuPath, JacksonUtil.writeValueAsString(roleNameList));

        // 判断当前用户是否可以访问当前请求
        return mono
            // 判断用户是否认证通过
            .filter(Authentication::isAuthenticated)
            .flatMapIterable(Authentication::getAuthorities)
            .map(GrantedAuthority::getAuthority)
            // 判断用户角色是否可以访问当前菜单
            .any(roleNameList::contains)
            .map(AuthorizationDecision::new)
            .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
