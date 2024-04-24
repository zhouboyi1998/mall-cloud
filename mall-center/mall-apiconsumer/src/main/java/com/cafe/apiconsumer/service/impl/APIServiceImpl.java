package com.cafe.apiconsumer.service.impl;

import com.cafe.apiconsumer.model.API;
import com.cafe.apiconsumer.service.APIService;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.enumeration.http.HttpMethodEnum;
import com.cafe.common.util.json.JacksonUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.apiconsumer.service.impl
 * @Author: zhouboyi
 * @Date: 2024/4/23 20:25
 * @Description:
 */
@Service
public class APIServiceImpl implements APIService {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public APIServiceImpl() {
        this.webClient = WebClient.builder().build();
    }

    @Override
    public Mono<ObjectNode> proxy(API api) {
        switch (HttpMethodEnum.getHttpMethodByName(api.getMethod())) {
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
            case UNKNOWN:
            default:
                LOGGER.error("APIServiceImpl.proxy(): Unknown HTTP Method! api -> {}", JacksonUtil.writeValueAsString(api));
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, "Unknown HTTP Method!"));
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
            .uri(api.getUrl(), uriBuilder -> {
                api.getQuery().forEach(uriBuilder::queryParam);
                return uriBuilder.build();
            })
            .headers(httpHeaders -> api.getHeader().forEach(httpHeaders::add))
            .retrieve()
            .bodyToMono(ObjectNode.class)
            .doOnSuccess(response -> LOGGER.info("APIServiceImpl.sendRequest(): api -> {}, response -> {}", JacksonUtil.writeValueAsString(api), response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                LOGGER.error("APIServiceImpl.sendRequest(): api -> {}, message -> {}", JacksonUtil.writeValueAsString(api), message, throwable);
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, message));
            });
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
            .uri(api.getUrl(), uriBuilder -> {
                api.getQuery().forEach(uriBuilder::queryParam);
                return uriBuilder.build();
            })
            .headers(httpHeaders -> api.getHeader().forEach(httpHeaders::add))
            .bodyValue(api.getBody())
            .retrieve()
            .bodyToMono(ObjectNode.class)
            .doOnSuccess(response -> LOGGER.info("APIServiceImpl.sendRequest(): api -> {}, response -> {}", JacksonUtil.writeValueAsString(api), response))
            .onErrorResume(throwable -> {
                String message = throwable.getMessage();
                LOGGER.error("APIServiceImpl.sendRequest(): api -> {}, message -> {}", JacksonUtil.writeValueAsString(api), message, throwable);
                return Mono.just(JacksonUtil.createObjectNode(StringConstant.MESSAGE, message));
            });
    }
}
