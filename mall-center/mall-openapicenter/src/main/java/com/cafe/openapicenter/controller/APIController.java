package com.cafe.openapicenter.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.openapicenter.model.API;
import com.cafe.openapicenter.service.APIService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.controller
 * @Author: zhouboyi
 * @Date: 2024/4/23 20:18
 * @Description: 通用开放接口消费者
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class APIController {

    private final APIService apiService;

    @ApiLogPrint(value = "通用代理")
    @PostMapping(value = "/proxy")
    public Mono<ObjectNode> proxy(@RequestBody API api) {
        return apiService.proxy(api);
    }
}
