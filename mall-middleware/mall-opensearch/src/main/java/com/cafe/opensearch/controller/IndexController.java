package com.cafe.opensearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.opensearch.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.support.master.AcknowledgedResponse;
import org.opensearch.client.indices.CreateIndexResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch.controller
 * @Author: zhouboyi
 * @Date: 2025/6/15 6:03
 * @Description: OpenSearch 索引接口
 */
@Api(value = "OpenSearch 索引接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    private final IndexService indexService;

    @ApiLogPrint(value = "判断索引是否存在")
    @ApiOperation(value = "判断索引是否存在")
    @ApiImplicitParam(value = "索引名称", name = "index", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{index}")
    public ResponseEntity<Boolean> exists(@PathVariable(value = "index") String index) {
        Boolean exists = indexService.exists(index);
        return ResponseEntity.ok(exists);
    }

    @ApiLogPrint(value = "创建索引")
    @ApiOperation(value = "创建索引")
    @ApiImplicitParam(value = "索引名称", name = "index", dataType = "String", paramType = "path", required = true)
    @PostMapping(value = "/{index}")
    public ResponseEntity<CreateIndexResponse> create(@PathVariable(value = "index") String index) {
        CreateIndexResponse createIndexResponse = indexService.create(index);
        return ResponseEntity.ok(createIndexResponse);
    }

    @ApiLogPrint(value = "删除索引")
    @ApiOperation(value = "删除索引")
    @ApiImplicitParam(value = "索引名称", name = "index", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{index}")
    public ResponseEntity<AcknowledgedResponse> delete(@PathVariable(value = "index") String index) {
        AcknowledgedResponse acknowledgedResponse = indexService.delete(index);
        return ResponseEntity.ok(acknowledgedResponse);
    }
}
