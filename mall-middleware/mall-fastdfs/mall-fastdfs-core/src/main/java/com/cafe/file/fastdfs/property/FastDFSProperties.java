package com.cafe.file.fastdfs.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.property
 * @Author: zhouboyi
 * @Date: 2023/3/13 21:22
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "fastdfs")
public class FastDFSProperties {

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
