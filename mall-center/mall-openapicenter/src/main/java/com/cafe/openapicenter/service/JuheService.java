package com.cafe.openapicenter.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.service
 * @Author: zhouboyi
 * @Date: 2024/4/21 4:45
 * @Description:
 */
public interface JuheService {

    /**
     * 查询手机号码归属地
     *
     * @param phone 手机号码
     * @param key   请求Key
     * @return 响应结果
     */
    Mono<ObjectNode> mobile2region(String phone, String key);

    /**
     * 查询IP地址归属地
     *
     * @param ip  IP地址
     * @param key 请求Key
     * @return 响应结果
     */
    Mono<ObjectNode> ip2region(String ip, String key);

    /**
     * 查询天气
     *
     * @param city 城市
     * @param key  请求Key
     * @return 响应结果
     */
    Mono<ObjectNode> weather(String city, String key);
}
