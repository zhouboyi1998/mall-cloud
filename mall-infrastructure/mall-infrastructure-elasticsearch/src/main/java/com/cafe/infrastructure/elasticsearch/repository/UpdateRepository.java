package com.cafe.infrastructure.elasticsearch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.repository
 * @Author: zhouboyi
 * @Date: 2025/4/17 16:24
 * @Description:
 */
@NoRepositoryBean
public interface UpdateRepository<T, ID> extends CrudRepository<T, ID> {

    /**
     * 修改文档
     *
     * @param entity 文档对象
     * @param <S>    文档类型
     * @return 被修改的文档对象
     */
    <S extends T> S update(S entity);

    /**
     * 修改文档列表
     *
     * @param entityIterable 文档列表
     * @param <S>            文档类型
     * @return 被修改的文档列表
     */
    <S extends T> Iterable<S> updateAll(Iterable<S> entityIterable);
}
