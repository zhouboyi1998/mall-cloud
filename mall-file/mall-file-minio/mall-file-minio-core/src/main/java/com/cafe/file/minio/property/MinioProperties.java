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
     * 存储桶名称
     */
    private String bucket;

    /**
     * 用户名
     */
    private String accessKey;

    /**
     * 密码
     */
    private String secretKey;

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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
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
}
