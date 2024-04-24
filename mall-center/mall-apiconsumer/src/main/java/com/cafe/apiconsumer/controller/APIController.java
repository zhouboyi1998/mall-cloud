package com.cafe.apiconsumer.controller;

import com.cafe.apiconsumer.model.API;
import com.cafe.apiconsumer.service.APIService;
import com.cafe.common.log.annotation.LogPrint;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.apiconsumer.controller
 * @Author: zhouboyi
 * @Date: 2024/4/23 20:18
 * @Description:
 */
@RestController
@RequestMapping(value = "/api")
public class APIController {

    private final APIService apiService;

    @Autowired
    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @LogPrint(value = "通用代理")
    @PostMapping(value = "/proxy")
    public Mono<ObjectNode> proxy(@RequestBody API api) {
        return apiService.proxy(api);
    }
}
