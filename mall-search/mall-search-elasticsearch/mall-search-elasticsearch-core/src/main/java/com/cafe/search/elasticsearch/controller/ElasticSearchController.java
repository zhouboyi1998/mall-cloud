package com.cafe.search.elasticsearch.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.search.elasticsearch.service.ElasticSearchService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:06
 * @Description:
 */
@RestController
@RequestMapping(value = "/elastic")
public class ElasticSearchController {

    private ElasticSearchService elasticSearchService;

    @Autowired
    public ElasticSearchController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @LogPrint(description = "查询 ElasticSearch 集群信息")
    @ApiOperation(value = "查询 ElasticSearch 集群信息")
    @GetMapping(value = "/info")
    public ResponseEntity<SearchResponse> info() throws IOException {
        return ResponseEntity.ok(elasticSearchService.info());
    }

    @LogPrint(description = "判断索引是否存在")
    @ApiOperation(value = "判断索引是否存在")
    @ApiImplicitParam(name = "name", value = "索引名称", required = true, paramType = "path", dataType = "String")
    @GetMapping(value = "/index/{name}")
    public ResponseEntity<Boolean> existsIndex(
        @PathVariable(value = "name") String name
    ) throws IOException {
        return ResponseEntity.ok(elasticSearchService.existsIndex(name));
    }

    @LogPrint(description = "创建索引")
    @ApiOperation(value = "创建索引")
    @ApiImplicitParam(name = "name", value = "索引名称", required = true, paramType = "path", dataType = "String")
    @PostMapping(value = "/index/{name}")
    public ResponseEntity<CreateIndexResponse> createIndex(
        @PathVariable(value = "name") String name
    ) throws IOException {
        return ResponseEntity.ok(elasticSearchService.createIndex(name));
    }

    @LogPrint(description = "删除索引")
    @ApiOperation(value = "删除索引")
    @ApiImplicitParam(name = "name", value = "索引名称", required = true, paramType = "path", dataType = "String")
    @DeleteMapping(value = "/index/{name}")
    public ResponseEntity<AcknowledgedResponse> deleteIndex(
        @PathVariable(value = "name") String name
    ) throws IOException {
        return ResponseEntity.ok(elasticSearchService.deleteIndex(name));
    }
}
