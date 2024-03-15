package com.cafe.minio.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.property
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:37
 * @Description: MinIO 配置
 */
@Getter
@Setter
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
     * 下载时使用的 HTTP Response 内容类型
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
}
