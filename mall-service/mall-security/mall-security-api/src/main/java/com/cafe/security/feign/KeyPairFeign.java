package com.cafe.security.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
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
@FeignClient(value = "mall-security", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/keypair")
public interface KeyPairFeign {

    /**
     * 获取 RSA 公钥
     *
     * @return
     */
    @GetMapping(value = "/rsa")
    ResponseEntity<Map<String, Object>> rsa();
}
