package com.cafe.security.service;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service
 * @Author: zhouboyi
 * @Date: 2022/7/8 10:09
 * @Description:
 */
public interface JWKSetService {

    /**
     * 获取 RSA JWKSet
     *
     * @return
     */
    Map<String, Object> rsa();
}
