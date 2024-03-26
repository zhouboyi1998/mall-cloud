package com.cafe.elasticsearch.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.elasticsearch.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2024/3/27 2:24
 * @Description: ElasticSearch 索引接口
 */
@Api(value = "ElasticSearch 索引接口")
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    private final IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @LogPrint(value = "判断索引是否存在")
    @ApiOperation(value = "判断索引是否存在")
    @ApiImplicitParam(value = "索引名称", name = "index", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{index}")
    public ResponseEntity<Boolean> exists(@PathVariable(value = "index") String index) throws IOException {
        Boolean exists = indexService.exists(index);
        return ResponseEntity.ok(exists);
    }

    @LogPrint(value = "创建索引")
    @ApiOperation(value = "创建索引")
    @ApiImplicitParam(value = "索引名称", name = "index", dataType = "String", paramType = "path", required = true)
    @PostMapping(value = "/{index}")
    public ResponseEntity<CreateIndexResponse> create(@PathVariable(value = "index") String index) throws IOException {
        CreateIndexResponse createIndexResponse = indexService.create(index);
        return ResponseEntity.ok(createIndexResponse);
    }

    @LogPrint(value = "删除索引")
    @ApiOperation(value = "删除索引")
    @ApiImplicitParam(value = "索引名称", name = "index", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{index}")
    public ResponseEntity<AcknowledgedResponse> delete(@PathVariable(value = "index") String index) throws IOException {
        AcknowledgedResponse acknowledgedResponse = indexService.delete(index);
        return ResponseEntity.ok(acknowledgedResponse);
    }
}
