package com.cafe.gateway.manage.authorization;

import cn.hutool.core.convert.Convert;
import com.cafe.common.constant.AuthenticationConstant;
import com.cafe.common.constant.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.manage.authorization
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:02
 * @Description: 授权管理器
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationManager.class);

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public AuthorizationManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取当前请求路径, 切割成数组
        String[] array = authorizationContext.getExchange().getRequest().getPath().toString().split("/");
        // 获取当前菜单路径
        StringBuilder path = new StringBuilder("/");
        if (array.length > AuthenticationConstant.MENU_PATH_INDEX) {
            path.append(array[AuthenticationConstant.MENU_PATH_INDEX]);
            LOGGER.info("AuthorizationManager.check(): menu-path -> {}", path);
        }
        // 获取可以访问当前菜单的角色列表
        Object list = redisTemplate.opsForHash().get(RedisConstant.MENU_ROLE_MAP, path.toString());
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
