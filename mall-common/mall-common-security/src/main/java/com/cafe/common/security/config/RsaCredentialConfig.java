package com.cafe.common.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.security.config
 * @Author: zhouboyi
 * @Date: 2022/5/13 11:12
 * @Description: 获取 application 中定义的 RSA 证书配置
 */
@Component
@ConfigurationProperties(prefix = "rsa.credential")
public class RsaCredentialConfig {

    /**
     * 证书文件名
     */
    private String filename;

    /**
     * 别名
     */
    private String alias;

    /**
     * 密钥口令
     */
    private String keypass;

    /**
     * 密钥库口令
     */
    private String storepass;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public String getStorepass() {
        return storepass;
    }

    public void setStorepass(String storepass) {
        this.storepass = storepass;
    }
}
