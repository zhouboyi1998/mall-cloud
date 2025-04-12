package com.cafe.starter.web.controlleradvice.response;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.constant.security.SecurityConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.starter.boot.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
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

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.web.controlleradvice.response
 * @Author: zhouboyi
 * @Date: 2023/7/11 17:31
 * @Description: 全局返回结果转换器
 */
@RestControllerAdvice(annotations = {RestController.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Order(value = IntegerConstant.TWO_HUNDRED)
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Value(value = "${spring.application.name}")
    private String applicationName;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 是否执行 beforeBodyWrite() 方法
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 微服务之间的 Feign 远程调用的返回结果, 不需要封装成 Result 类型
        if (Boolean.parseBoolean(request.getHeaders().getFirst(RequestConstant.Header.IS_FEIGN))) {
            return body;
        }
        // 安全服务获取 JWK 公钥接口的返回结果, 不需要封装 Result 类型
        if (Objects.equals(applicationName, ServiceConstant.MALL_SECURITY) &&
            Objects.equals(request.getURI().getPath(), SecurityConstant.JWK_SET_URI_PATH)) {
            return body;
        }
        // 返回结果已经是 Result 类型时, 不需要再次封装
        if (body instanceof Result) {
            return body;
        }
        // 返回结果是 String 类型时, 封装成 Result 类型后, 需要手动序列化成 JSON 格式, 否则会出现 ClassCastException 异常
        if (body instanceof String) {
            // 设置 Content-Type = application/json
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return JacksonUtil.writeValueAsString(Result.success(body));
        }
        // 返回结果封装成 Result 类型
        return Result.success(body);
    }
}
