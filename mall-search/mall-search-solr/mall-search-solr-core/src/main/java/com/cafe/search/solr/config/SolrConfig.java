package com.cafe.search.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.config
 * @Author: zhouboyi
 * @Date: 2022/10/26 16:12
 * @Description:
 */
@Configuration
public class SolrConfig {

    private SolrClient solrClient;

    @Autowired
    public SolrConfig(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Bean
    public SolrTemplate solrTemplate() {
        return new SolrTemplate(solrClient);
    }
}
