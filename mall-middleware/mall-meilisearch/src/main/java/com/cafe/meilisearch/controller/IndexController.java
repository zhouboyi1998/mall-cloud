package com.cafe.meilisearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.meilisearch.model.dto.IndexDTO;
import com.cafe.meilisearch.model.dto.Page;
import com.cafe.meilisearch.service.IndexService;
import com.meilisearch.sdk.model.IndexesQuery;
import com.meilisearch.sdk.model.TaskInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.controller
 * @Author: zhouboyi
 * @Date: 2025/6/2 2:33
 * @Description: MeiliSearch 索引接口
 */
@Api(value = "MeiliSearch 索引接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    private final IndexService indexService;

    @ApiLogPrint(value = "查询索引列表")
    @ApiOperation(value = "查询索引列表")
    @GetMapping(value = "")
    public ResponseEntity<List<IndexDTO>> list() {
        List<IndexDTO> indexDTOList = indexService.list();
        return ResponseEntity.ok(indexDTOList);
    }

    @ApiLogPrint(value = "分页查询索引列表")
    @ApiOperation(value = "分页查询索引列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "偏移量", name = "offset", dataType = "Integer", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "limit", dataType = "Integer", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{offset}/{limit}")
    public ResponseEntity<Page<IndexDTO>> page(
        @PathVariable(value = "offset") Integer offset,
        @PathVariable(value = "limit") Integer limit
    ) {
        IndexesQuery indexesQuery = new IndexesQuery().setOffset(offset).setLimit(limit);
        Page<IndexDTO> indexDTOPage = indexService.page(indexesQuery);
        return ResponseEntity.ok(indexDTOPage);
    }

    @ApiLogPrint(value = "根据uid查询单个索引")
    @ApiOperation(value = "根据uid查询单个索引")
    @ApiImplicitParam(value = "索引uid", name = "uid", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{uid}")
    public ResponseEntity<IndexDTO> one(@PathVariable(value = "uid") String uid) {
        IndexDTO indexDTO = indexService.one(uid);
        return ResponseEntity.ok(indexDTO);
    }

    @ApiLogPrint(value = "创建索引")
    @ApiOperation(value = "创建索引")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "索引uid", name = "uid", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "索引的主键字段", name = "primaryKey", dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/{uid}")
    public ResponseEntity<TaskInfo> create(
        @PathVariable(value = "uid") String uid,
        @RequestParam(value = "primaryKey", required = false) String primaryKey
    ) {
        TaskInfo taskInfo = indexService.create(uid, primaryKey);
        return ResponseEntity.ok(taskInfo);
    }

    @ApiLogPrint(value = "修改索引的主键字段")
    @ApiOperation(value = "修改索引的主键字段")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "索引uid", name = "uid", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "索引的主键字段", name = "primaryKey", dataType = "String", paramType = "query")
    })
    @PutMapping(value = "/{uid}")
    public ResponseEntity<TaskInfo> update(
        @PathVariable(value = "uid") String uid,
        @RequestParam(value = "primaryKey", required = false) String primaryKey
    ) {
        TaskInfo taskInfo = indexService.update(uid, primaryKey);
        return ResponseEntity.ok(taskInfo);
    }

    @ApiLogPrint(value = "删除索引")
    @ApiOperation(value = "删除索引")
    @ApiImplicitParam(value = "索引uid", name = "uid", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<TaskInfo> delete(@PathVariable(value = "uid") String uid) {
        TaskInfo taskInfo = indexService.delete(uid);
        return ResponseEntity.ok(taskInfo);
    }
}
