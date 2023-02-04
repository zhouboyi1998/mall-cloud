package com.cafe.search.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr
 * @Author: zhouboyi
 * @Date: 2022/10/26 14:30
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.cafe")
public class SolrApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolrApplication.class, args);
    }
}
