package com.cafe.infrastructure.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.repository
 * @Author: zhouboyi
 * @Date: 2025/4/17 17:25
 * @Description:
 */
@NoRepositoryBean
public interface ExtensionElasticsearchRepository<T, ID> extends ElasticsearchRepository<T, ID>, UpdateRepository<T, ID> {

}
