package com.cafe.search.solr;

import com.cafe.common.constant.app.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr
 * @Author: zhouboyi
 * @Date: 2022/10/26 14:30
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = AppConstant.DEFAULT_PACKAGE)
public class SolrApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolrApplication.class, args);
    }
}
