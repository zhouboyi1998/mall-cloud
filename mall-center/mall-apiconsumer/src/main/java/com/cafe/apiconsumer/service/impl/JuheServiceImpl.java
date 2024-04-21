package com.cafe.apiconsumer.service.impl;

import com.cafe.apiconsumer.service.JuheService;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.apiconsumer.service.impl
 * @Author: zhouboyi
 * @Date: 2024/4/21 4:47
 * @Description:
 */
@Service
public class JuheServiceImpl implements JuheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JuheServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public JuheServiceImpl() {
        this.webClient = WebClient.builder()
            .baseUrl("https://apis.juhe.cn")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();
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
            .doOnSuccess(response -> LOGGER.info("JuheServiceImpl.mobile2region(): api id -> 11, response -> {}", response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                LOGGER.error("JuheServiceImpl.mobile2region(): api id -> 11, message -> {}", message, throwable);
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
            .doOnSuccess(response -> LOGGER.info("JuheServiceImpl.ip2region(): api id -> 1, response -> {}", response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                LOGGER.error("JuheServiceImpl.ip2region(): api id -> 1, message -> {}", message, throwable);
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
            .doOnSuccess(response -> LOGGER.info("JuheServiceImpl.weather(): api id -> 73, response -> {}", response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                LOGGER.error("JuheServiceImpl.weather(): api id -> 73, message -> {}", message, throwable);
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, message));
            });
    }
}
