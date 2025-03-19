package com.cafe.openapicenter.service;

import com.cafe.openapicenter.model.entity.API;
import com.fasterxml.jackson.databind.node.ObjectNode;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.service
 * @Author: zhouboyi
 * @Date: 2024/4/23 20:24
 * @Description:
 */
public interface APIService {

    /**
     * 通用代理
     *
     * @param api 请求参数
     * @return 响应结果
     */
    Mono<ObjectNode> proxy(API api);
}
