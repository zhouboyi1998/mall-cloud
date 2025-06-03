package com.cafe.meilisearch.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.property
 * @Author: zhouboyi
 * @Date: 2025/6/2 1:48
 * @Description: MeiliSearch 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "meilisearch")
public class MeiliSearchProperties {

    /**
     * 主机名
     */
    private String hostUrl;

    /**
     * API 密钥
     */
    private String apiKey;
}
