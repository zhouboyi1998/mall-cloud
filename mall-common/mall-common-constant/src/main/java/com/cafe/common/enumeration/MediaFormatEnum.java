package com.cafe.common.enumeration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration
 * @Author: zhouboyi
 * @Date: 2023/2/25 0:35
 * @Description: 媒体文件格式枚举
 */
public enum MediaFormatEnum {

    /**
     * PNG 格式
     */
    PNG("png", "data:image/png;base64,"),

    /**
     * JPG 格式
     */
    JPG("jpg", "data:image/jpg;base64,");

    /**
     * 格式
     */
    private final String format;

    /**
     * Base64 编码前缀
     */
    private final String base64Prefix;

    MediaFormatEnum(String format, String base64Prefix) {
        this.format = format;
        this.base64Prefix = base64Prefix;
    }

    public String getFormat() {
        return format;
    }

    public String getBase64Prefix() {
        return base64Prefix;
    }
}
