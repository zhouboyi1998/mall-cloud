package com.cafe.common.core.response;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.core.result.Result;
import com.cafe.common.util.json.JacksonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.net.URI;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.response
 * @Author: zhouboyi
 * @Date: 2023/7/11 17:31
 * @Description: 全局返回结果转换器
 */
@RestControllerAdvice(annotations = {RestController.class})
@ConditionalOnClass(value = ResponseBodyAdvice.class)
@Order(value = IntegerConstant.TWO_HUNDRED)
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Value(value = "${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private URI jwtSetUri;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 判断是否执行 beforeBodyWrite() 方法
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // Feign 请求直接返回
        if (Boolean.parseBoolean(request.getHeaders().getFirst(RequestConstant.Header.IS_FEIGN))) {
            return body;
        }
        // JWT SET 接口直接返回
        if (Objects.equals(request.getURI().getPath(), jwtSetUri.getPath().replace(AppConstant.MALL_SECURITY_PREFIX, StringConstant.EMPTY))) {
            return body;
        }
        // 返回值已经是 Result 类型时不需要再次封装
        if (body instanceof Result) {
            return body;
        }
        // 返回值是 String 类型时, 需要手动将 Result 转换成 JSON 字符串, 否则会出现 ClassCastException
        if (body instanceof String) {
            // 设置 Content-Type = application/json
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return JacksonUtil.writeValueAsString(Result.success(body));
        }
        // 返回结果封装成 Result 类型
        return Result.success(body);
    }
}
