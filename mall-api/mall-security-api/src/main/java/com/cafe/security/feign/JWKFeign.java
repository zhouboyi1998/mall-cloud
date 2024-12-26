package com.cafe.security.feign;

import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.feign
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:10
 * @Description:
 */
@FeignClient(value = "mall-security", contextId = "jwk", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/jwk")
public interface JWKFeign {

    /**
     * 获取 JWK 公钥 (JWKSet 格式)
     *
     * @return JWK 公钥 (JWKSet 格式)
     */
    @GetMapping(value = "/jwk-set")
    ResponseEntity<Map<String, Object>> jwkSet();

    /**
     * 获取 JWK 公钥 (Base64 格式)
     *
     * @return JWK 公钥 (Base64 格式)
     */
    @GetMapping(value = "/base64")
    ResponseEntity<String> base64();
}
