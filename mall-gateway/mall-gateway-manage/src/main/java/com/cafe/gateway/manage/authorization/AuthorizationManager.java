package com.cafe.gateway.manage.authorization;

import cn.hutool.core.convert.Convert;
import com.cafe.common.constant.AuthenticationConstant;
import com.cafe.common.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.manage.authorization
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:02
 * @Description: 授权管理器
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
        // 获取所有角色列表 (拥有任意角色权限即可访问所有接口)
        Long size = redisTemplate.opsForList().size(RedisConstant.ROLE_NAME_LIST);
        Object obj = redisTemplate.opsForList().range(RedisConstant.ROLE_NAME_LIST, 0, size);
        List<String> authorities = Convert.toList(String.class, obj);
        authorities = authorities
            .stream()
            .map(i -> i = AuthenticationConstant.AUTHORITY_PREFIX + i)
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
