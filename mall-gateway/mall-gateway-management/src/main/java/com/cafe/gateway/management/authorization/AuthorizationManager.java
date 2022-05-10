package com.cafe.gateway.management.authorization;

import cn.hutool.core.convert.Convert;
import com.cafe.security.management.constant.AuthEnum;
import com.cafe.security.management.constant.RedisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.authorization
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:02
 * @Description:
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public AuthorizationManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 从 Redis 中获取当前路径可访问角色列表
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        Object obj = redisTemplate.opsForHash().get(RedisEnum.RESOURCE_ROLE_MAP.getValue(), uri.getPath());
        List<String> authorities = Convert.toList(String.class, obj);
        authorities = authorities
            .stream()
            .map(i -> i = AuthEnum.AUTHORITY_PREFIX.getValue() + i)
            .collect(Collectors.toList());

        // 认证通过且角色匹配的用户可访问当前路径
        return mono
            .filter(Authentication::isAuthenticated)
            .flatMapIterable(Authentication::getAuthorities)
            .map(GrantedAuthority::getAuthority)
            .any(authorities::contains)
            .map(AuthorizationDecision::new)
            .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
