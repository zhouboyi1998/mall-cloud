package com.cafe.security.service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service
 * @Author: zhouboyi
 * @Date: 2023/8/16 16:06
 * @Description:
 */
public interface RSAService {

    /**
     * 获取 RSA 公钥
     *
     * @return
     */
    String publicKey();
}
