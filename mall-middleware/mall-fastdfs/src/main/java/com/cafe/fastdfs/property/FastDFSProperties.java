package com.cafe.fastdfs.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.property
 * @Author: zhouboyi
 * @Date: 2023/3/13 21:22
 * @Description: FastDFS 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "fastdfs")
public class FastDFSProperties {

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
