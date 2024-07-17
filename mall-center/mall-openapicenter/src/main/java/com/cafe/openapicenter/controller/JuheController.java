package com.cafe.openapicenter.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.openapicenter.service.JuheService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.controller
 * @Author: zhouboyi
 * @Date: 2024/4/21 4:45
 * @Description: 聚合数据开放接口消费者
 */
@RestController
@RequestMapping(value = "/juhe")
public class JuheController {

    private final JuheService juheService;

    @Autowired
    public JuheController(JuheService juheService) {
        this.juheService = juheService;
    }

    @ApiLogPrint(value = "查询手机号码归属地")
    @GetMapping(value = "/mobile2region")
    public Mono<ObjectNode> mobile2region(
        @RequestParam(value = "phone") String phone,
        @RequestParam(value = "key") String key
    ) {
        return juheService.mobile2region(phone, key);
    }

    @ApiLogPrint(value = "查询IP地址归属地")
    @GetMapping(value = "/ip2region")
    public Mono<ObjectNode> ip2region(
        @RequestParam(value = "ip") String ip,
        @RequestParam(value = "key") String key
    ) {
        return juheService.ip2region(ip, key);
    }

    @ApiLogPrint(value = "查询天气")
    @GetMapping(value = "/weather")
    public Mono<ObjectNode> weather(
        @RequestParam(value = "city") String city,
        @RequestParam(value = "key") String key
    ) {
        return juheService.weather(city, key);
    }
}
