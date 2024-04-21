package com.cafe.apiconsumer.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.apiconsumer.service
 * @Author: zhouboyi
 * @Date: 2024/4/21 4:45
 * @Description:
 */
public interface JuheService {

    /**
     * 查询手机号码归属地
     *
     * @param phone
     * @param key
     * @return
     */
    Mono<ObjectNode> mobile2region(String phone, String key);

    /**
     * 查询IP地址归属地
     *
     * @param ip
     * @param key
     * @return
     */
    Mono<ObjectNode> ip2region(String ip, String key);

    /**
     * 查询天气
     *
     * @param city
     * @param key
     * @return
     */
    Mono<ObjectNode> weather(String city, String key);
}
