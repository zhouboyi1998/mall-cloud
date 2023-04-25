package com.cafe.search.elasticsearch.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.property
 * @Author: zhouboyi
 * @Date: 2023/4/25 11:56
 * @Description: ElasticSearch 配置
 */
@Component
@ConfigurationProperties(prefix = "elastic-search")
public class ElasticSearchProperties {

    private String host;

    private Integer port;

    private String schema;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
