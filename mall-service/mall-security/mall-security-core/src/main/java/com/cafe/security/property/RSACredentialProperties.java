package com.cafe.security.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.property
 * @Author: zhouboyi
 * @Date: 2022/5/13 11:12
 * @Description: RSA 证书配置
 */
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

    public String getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }

    public String getStorePass() {
        return storePass;
    }

    public void setStorePass(String storePass) {
        this.storePass = storePass;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getKeyPass() {
        return keyPass;
    }

    public void setKeyPass(String keyPass) {
        this.keyPass = keyPass;
    }
}
