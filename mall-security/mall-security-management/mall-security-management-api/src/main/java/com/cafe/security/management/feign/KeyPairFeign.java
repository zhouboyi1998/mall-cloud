package com.cafe.security.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.feign
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:10
 * @Description:
 */
@FeignClient("mall-security-management")
@RequestMapping("/keyPair")
public interface KeyPairFeign {

    /**
     * 获取 RSA 私钥
     *
     * @return
     */
    @GetMapping("/rsa/publicKey")
    ResponseEntity<Map<String, Object>> getRsaPublicKey();
}
