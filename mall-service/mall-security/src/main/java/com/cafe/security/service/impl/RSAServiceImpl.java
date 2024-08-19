package com.cafe.security.service.impl;

import com.cafe.security.service.RSAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Base64;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2023/8/16 16:06
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class RSAServiceImpl implements RSAService {

    /**
     * 密钥对
     */
    private final KeyPair keyPair;

    @Override
    public String publicKey() {
        // 获取 RSA 公钥, 使用 Base64 编码返回
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }
}
