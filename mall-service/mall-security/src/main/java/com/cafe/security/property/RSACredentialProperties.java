package com.cafe.security.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.property
 * @Author: zhouboyi
 * @Date: 2022/5/13 11:12
 * @Description: RSA 证书配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rsa-credential")
public class RSACredentialProperties {

    /**
     * 密钥库名称
     */
    private String keyStore;

    /**
     * 密钥库口令
     */
    private String storePass;

    /**
     * 别名
     */
    private String alias;

    /**
     * 密钥口令
     */
    private String keyPass;
}
