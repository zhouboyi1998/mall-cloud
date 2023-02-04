package com.cafe.file.minio.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.property
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:37
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * 服务地址
     */
    private String endpoint;

    /**
     * 文件地址
     */
    private String fileHost;

    /**
     * 用户名
     */
    private String accessKey;

    /**
     * 密码
     */
    private String secretKey;

    /**
     * 字符集&编码
     */
    private String characterEncoding;

    /**
     * 下载时的 HTTP Response 内容类型
     */
    private String contentType;

    /**
     * 下载时使用的 HTTP Header Key
     */
    private String headerKey;

    /**
     * 下载时使用的 HTTP Header Value 前缀
     */
    private String headerValuePrefix;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getFileHost() {
        return fileHost;
    }

    public void setFileHost(String fileHost) {
        this.fileHost = fileHost;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public String getHeaderValuePrefix() {
        return headerValuePrefix;
    }

    public void setHeaderValuePrefix(String headerValuePrefix) {
        this.headerValuePrefix = headerValuePrefix;
    }
}
