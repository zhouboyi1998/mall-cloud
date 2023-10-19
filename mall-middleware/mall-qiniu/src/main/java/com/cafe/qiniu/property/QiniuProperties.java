package com.cafe.qiniu.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.property
 * @Author: zhouboyi
 * @Date: 2023/10/18 11:34
 * @Description: Qiniu 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {

    /**
     * 域名地址
     */
    private String url;

    /**
     * 公钥
     */
    private String accessKey;

    /**
     * 私钥
     */
    private String secretKey;

    /**
     * 存储区域
     */
    private String region;
}
