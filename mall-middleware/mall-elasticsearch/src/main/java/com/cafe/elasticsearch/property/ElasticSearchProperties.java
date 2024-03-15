package com.cafe.elasticsearch.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.property
 * @Author: zhouboyi
 * @Date: 2023/4/25 11:56
 * @Description: ElasticSearch 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "elastic-search")
public class ElasticSearchProperties {

    /**
     * 主机名
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 网络连接策略
     */
    private String scheme;
}
