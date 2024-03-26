package com.cafe.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:19
 * @Description:
 */
public interface ServerService {

    /**
     * 查询服务信息
     *
     * @return
     * @throws IOException
     */
    SearchResponse info() throws IOException;
}
