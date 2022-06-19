package com.cafe.security.manage.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.property
 * @Author: zhouboyi
 * @Date: 2022/5/13 11:12
 * @Description: 获取 application 中定义的 RSA 证书配置
 */
@Component
@ConfigurationProperties(prefix = "rsa.credential")
public class RsaCredentialProperties {

    /**
     * 密钥库名称
     */
    private String keystore;

    /**
     * 密钥库口令
     */
    private String storepass;

    /**
     * 别名
     */
    private String alias;

    /**
     * 密钥口令
     */
    private String keypass;

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getStorepass() {
        return storepass;
    }

    public void setStorepass(String storepass) {
        this.storepass = storepass;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getKeypass() {
        return keypass;
    }

    public void setKeypass(String keypass) {
        this.keypass = keypass;
    }
}
