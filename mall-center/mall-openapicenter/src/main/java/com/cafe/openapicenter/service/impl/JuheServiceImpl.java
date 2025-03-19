package com.cafe.openapicenter.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.openapicenter.service.JuheService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.service.impl
 * @Author: zhouboyi
 * @Date: 2024/4/21 4:47
 * @Description:
 */
@Slf4j
@Service
public class JuheServiceImpl implements JuheService {

    private final WebClient webClient;

    @Autowired
    public JuheServiceImpl(@Qualifier(value = "webClient4Juhe") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ObjectNode> mobile2region(String phone, String key) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/mobile/get")
                .queryParam("phone", phone)
                .queryParam("key", key)
                .queryParam("dtype", "json")
                .build())
            .retrieve()
            .bodyToMono(ObjectNode.class)
            .doOnSuccess(response -> log.info("JuheServiceImpl.mobile2region(): api id -> 11, response -> {}", response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                log.error("JuheServiceImpl.mobile2region(): api id -> 11, message -> {}", message, throwable);
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, message));
            });
    }

    @Override
    public Mono<ObjectNode> ip2region(String ip, String key) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/ip/ipNewV3")
                .queryParam("ip", ip)
                .queryParam("key", key)
                .build())
            .retrieve()
            .bodyToMono(ObjectNode.class)
            .doOnSuccess(response -> log.info("JuheServiceImpl.ip2region(): api id -> 1, response -> {}", response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                log.error("JuheServiceImpl.ip2region(): api id -> 1, message -> {}", message, throwable);
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, message));
            });
    }

    @Override
    public Mono<ObjectNode> weather(String city, String key) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/simpleWeather/query")
                .queryParam("city", city)
                .queryParam("key", key)
                .build())
            .retrieve()
            .bodyToMono(ObjectNode.class)
            .doOnSuccess(response -> log.info("JuheServiceImpl.weather(): api id -> 73, response -> {}", response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                log.error("JuheServiceImpl.weather(): api id -> 73, message -> {}", message, throwable);
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, message));
            });
    }
}
