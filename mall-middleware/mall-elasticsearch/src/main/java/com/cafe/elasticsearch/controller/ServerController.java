package com.cafe.elasticsearch.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.elasticsearch.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:06
 * @Description: ElasticSearch 服务接口
 */
@Api(value = "ElasticSearch 服务接口")
@RestController
@RequestMapping(value = "/server")
public class ServerController {

    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @LogPrint(value = "查询服务信息")
    @ApiOperation(value = "查询服务信息")
    @GetMapping(value = "/info")
    public ResponseEntity<SearchResponse> info() throws IOException {
        SearchResponse searchResponse = serverService.info();
        return ResponseEntity.ok(searchResponse);
    }
}
