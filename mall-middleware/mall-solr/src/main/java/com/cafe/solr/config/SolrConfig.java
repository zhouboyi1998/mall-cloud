package com.cafe.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.solr.config
 * @Author: zhouboyi
 * @Date: 2022/10/26 16:12
 * @Description: Solr 配置类
 */
@Configuration
public class SolrConfig {

    private final SolrClient solrClient;

    @Autowired
    public SolrConfig(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Bean
    public SolrTemplate solrTemplate() {
        return new SolrTemplate(solrClient);
    }
}
