package com.cafe.opensearch.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch.property
 * @Author: zhouboyi
 * @Date: 2025/6/15 16:23
 * @Description: OpenSearch 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "opensearch")
public class OpenSearchProperties {

    /**
     * 协议
     */
    private String protocol;

    /**
     * 主机名
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
