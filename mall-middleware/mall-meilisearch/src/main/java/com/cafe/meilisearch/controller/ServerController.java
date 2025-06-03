package com.cafe.meilisearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.meilisearch.service.ServerService;
import com.meilisearch.sdk.model.Stats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.controller
 * @Author: zhouboyi
 * @Date: 2025/6/4 23:51
 * @Description:
 */
@Api(value = "MeiliSearch 服务接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/server")
public class ServerController {

    private final ServerService serverService;

    @ApiLogPrint(value = "查询服务健康状况信息")
    @ApiOperation(value = "查询服务健康状况信息")
    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        String health = serverService.health();
        return ResponseEntity.ok(health);
    }

    @ApiLogPrint(value = "查询服务健康状况")
    @ApiOperation(value = "查询服务健康状况")
    @GetMapping(value = "/is-healthy")
    public ResponseEntity<Boolean> isHealthy() {
        Boolean isHealthy = serverService.isHealthy();
        return ResponseEntity.ok(isHealthy);
    }

    @ApiLogPrint(value = "查询服务统计信息")
    @ApiOperation(value = "查询服务统计信息")
    @GetMapping(value = "/stats")
    public ResponseEntity<Stats> stats() {
        Stats stats = serverService.stats();
        return ResponseEntity.ok(stats);
    }

    @ApiLogPrint(value = "查询服务版本号")
    @ApiOperation(value = "查询服务版本号")
    @GetMapping(value = "/version")
    public ResponseEntity<String> version() {
        String version = serverService.version();
        return ResponseEntity.ok(version);
    }
}
