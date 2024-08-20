package com.cafe.openapicenter.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.openapicenter.model.API;
import com.cafe.openapicenter.service.APIService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.service.impl
 * @Author: zhouboyi
 * @Date: 2024/4/23 20:25
 * @Description:
 */
@Slf4j
@Service
public class APIServiceImpl implements APIService {

    private final WebClient webClient;

    @Autowired
    public APIServiceImpl() {
        this.webClient = WebClient.create();
    }

    @Override
    public Mono<ObjectNode> proxy(API api) {
        switch (HttpMethod.valueOf(api.getMethod())) {
            case GET:
                return sendRequest(api, webClient.get());
            case POST:
                return sendRequest(api, webClient.post());
            case PUT:
                return sendRequest(api, webClient.put());
            case PATCH:
                return sendRequest(api, webClient.patch());
            case DELETE:
                return sendRequest(api, webClient.delete());
            case HEAD:
                return sendRequest(api, webClient.head());
            case OPTIONS:
                return sendRequest(api, webClient.options());
            case TRACE:
            default:
                log.error("APIServiceImpl.proxy(): Unsupported HTTP Method! api -> {}", JacksonUtil.writeValueAsString(api));
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, "Unsupported HTTP Method!"));
        }
    }

    /**
     * 发送请求 (GET / DELETE / HEAD / OPTIONS)
     *
     * @param api
     * @param spec
     * @return
     */
    private Mono<ObjectNode> sendRequest(API api, WebClient.RequestHeadersUriSpec<?> spec) {
        return spec
            .uri(api.getUrl(), uriBuilder -> uri(api, uriBuilder))
            .headers(httpHeaders -> api.getHeader().forEach(httpHeaders::add))
            .retrieve()
            .bodyToMono(ObjectNode.class)
            .doOnSuccess(response -> doOnSuccess(api, response))
            .onErrorResume(throwable -> onErrorResume(api, throwable));
    }

    /**
     * 发送请求 (POST / PUT / PATCH)
     *
     * @param api
     * @param spec
     * @return
     */
    private Mono<ObjectNode> sendRequest(API api, WebClient.RequestBodyUriSpec spec) {
        return spec
            .uri(api.getUrl(), uriBuilder -> uri(api, uriBuilder))
            .headers(httpHeaders -> api.getHeader().forEach(httpHeaders::add))
            .bodyValue(api.getBody())
            .retrieve()
            .bodyToMono(ObjectNode.class)
            .doOnSuccess(response -> doOnSuccess(api, response))
            .onErrorResume(throwable -> onErrorResume(api, throwable));
    }

    /**
     * 构建 URI
     *
     * @param api
     * @param uriBuilder
     * @return
     */
    private URI uri(API api, UriBuilder uriBuilder) {
        api.getQuery().forEach(uriBuilder::queryParam);
        return uriBuilder.build();
    }

    /**
     * doOnSuccess 处理逻辑
     *
     * @param api
     * @param response
     */
    private void doOnSuccess(API api, ObjectNode response) {
        log.info("APIServiceImpl.doOnSuccess(): api -> {}, response -> {}", JacksonUtil.writeValueAsString(api), response);
    }

    /**
     * onErrorResume 处理逻辑
     *
     * @param api
     * @param throwable
     * @return
     */
    private Mono<ObjectNode> onErrorResume(API api, Throwable throwable) {
        log.error("APIServiceImpl.onErrorResume(): api -> {}, message -> {}", JacksonUtil.writeValueAsString(api), throwable.getMessage(), throwable);
        return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, throwable.getMessage()));
    }
}
