package com.cafe.security.manage.service;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.service
 * @Author: zhouboyi
 * @Date: 2022/7/8 10:09
 * @Description:
 */
public interface KeyPairService {

    /**
     * 获取 RSA 公钥
     * @return
     */
    Map<String, Object> getRsaPublicKey();
}
