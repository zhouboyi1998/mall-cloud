package com.cafe.apiconsumer.service;

import com.cafe.apiconsumer.model.API;
import com.fasterxml.jackson.databind.node.ObjectNode;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.apiconsumer.service
 * @Author: zhouboyi
 * @Date: 2024/4/23 20:24
 * @Description:
 */
public interface APIService {

    /**
     * 通用代理
     *
     * @param api
     * @return
     */
    Mono<ObjectNode> proxy(API api);
}
