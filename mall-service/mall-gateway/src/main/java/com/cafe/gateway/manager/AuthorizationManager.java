package com.cafe.gateway.manager;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.json.util.JacksonUtil;
import com.cafe.gateway.util.RequestUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        // 获取请求
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 获取请求头
        HttpHeaders httpHeaders = request.getHeaders();

        // 获取访问令牌
        Boolean accessTokenExist = Optional.ofNullable(RequestUtil.getAccessTokenByAuthorization(httpHeaders))
            // 判断访问令牌在令牌存储器中是否存在
            .map(accessToken -> redisTemplate.hasKey(RedisConstant.TOKEN_PREFIX + RedisConstant.ACCESS_TOKEN_INFIX + accessToken))
            .orElse(Boolean.FALSE);
        // 如果访问令牌不存在或已失效, 拒绝访问
        if (!accessTokenExist) {
            return Mono.just(new AuthorizationDecision(false));
        }

        // 获取请求类型
        HttpMethod httpMethod = request.getMethod();
        // 获取请求路径
        RequestPath requestPath = request.getPath();

        // 根据请求类型和请求路径组装 Redis Key 集合
        Set<String> redisKeySet = assembleRedisKeySet(httpMethod, requestPath);
        // 从 Redis 中批量获取可以访问当前请求路径的角色列表
        Set<String> roleNameSet = JacksonUtil.convertValue(redisTemplate.opsForHash().multiGet(RedisConstant.RESOURCE_ROLE_MAP, new HashSet<>(redisKeySet)), new TypeReference<List<List<String>>>() {})
            .stream()
            .filter(Objects::nonNull)
            .flatMap(Collection::stream)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
        log.info("AuthorizationManager.check(): http method -> {}, request path -> {}, role name set -> {}", httpMethod, requestPath, ToStringBuilder.reflectionToString(roleNameSet, ToStringStyle.JSON_STYLE));

        // 判断用户是否认证通过
        return mono.filter(Authentication::isAuthenticated)
            // 获取用户拥有的角色
            .flatMapIterable(Authentication::getAuthorities)
            .map(GrantedAuthority::getAuthority)
            // 判断用户拥有的的角色是否可以访问当前请求路径
            .any(roleNameSet::contains)
            .map(AuthorizationDecision::new)
            .defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * 组装 Redis Key 集合
     *
     * @param httpMethod  请求类型
     * @param requestPath 请求路径
     * @return Redis Key 集合
     */
    private Set<String> assembleRedisKeySet(HttpMethod httpMethod, RequestPath requestPath) {
        Set<String> redisKeySet = new HashSet<>();

        // 将请求类型和请求路径转换成字符串格式
        String methodPrefix = Optional.ofNullable(httpMethod)
            .map(HttpMethod::name)
            .map(String::toUpperCase)
            .map(method -> method + StringConstant.COLON)
            .orElse(StringConstant.EMPTY);
        String path = requestPath.value();

        // 处理根路径的情况
        if (StringUtils.isEmpty(path) || Objects.equals(path, StringConstant.SLASH)) {
            String rootPath = StringConstant.SLASH + StringConstant.DOUBLE_ASTERISK;
            redisKeySet.add(rootPath);
            redisKeySet.add(methodPrefix + rootPath);
            return redisKeySet;
        }

        // 添加根据原始路径生成的 Redis Key
        redisKeySet.add(path);
        redisKeySet.add(methodPrefix + path);

        // 添加根据每一层父路径生成的 Redis Key
        String[] segments = path.split(StringConstant.SLASH);
        for (int i = segments.length - 1; i > 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < i; j++) {
                sb.append(StringConstant.SLASH).append(segments[j]);
            }
            String parentPath = sb.append(StringConstant.SLASH).append(StringConstant.DOUBLE_ASTERISK).toString();
            redisKeySet.add(parentPath);
            redisKeySet.add(methodPrefix + parentPath);
        }

        return redisKeySet;
    }
}
