package com.cafe.minio.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.minio.service.BucketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.controller
 * @Author: zhouboyi
 * @Date: 2024/3/26 15:07
 * @Description: MinIO 存储桶接口
 */
@Api(value = "MinIO 存储桶接口")
@RestController
@RequestMapping(value = "/bucket")
public class BucketController {

    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @ApiLogPrint(value = "查询存储桶列表")
    @ApiOperation(value = "查询存储桶列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<String>> list() throws Exception {
        List<String> bucketList = bucketService.list();
        return ResponseEntity.ok(bucketList);
    }

    @ApiLogPrint(value = "查询存储桶是否存在")
    @ApiOperation(value = "查询存储桶是否存在")
    @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/exists/{bucket}")
    public ResponseEntity<Boolean> exists(@PathVariable(value = "bucket") String bucket) throws Exception {
        Boolean exists = bucketService.exists(bucket);
        return ResponseEntity.ok(exists);
    }

    @ApiLogPrint(value = "新建存储桶")
    @ApiOperation(value = "新建存储桶")
    @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true)
    @PostMapping(value = "/create/{bucket}")
    public ResponseEntity<Void> create(@PathVariable(value = "bucket") String bucket) throws Exception {
        bucketService.create(bucket);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "删除存储桶")
    @ApiOperation(value = "删除存储桶")
    @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/remove/{bucket}")
    public ResponseEntity<Void> remove(@PathVariable(value = "bucket") String bucket) throws Exception {
        bucketService.remove(bucket);
        return ResponseEntity.ok().build();
    }
}
