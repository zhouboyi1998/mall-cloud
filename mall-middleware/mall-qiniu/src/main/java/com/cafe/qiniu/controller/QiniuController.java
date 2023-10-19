package com.cafe.qiniu.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.qiniu.service.QiniuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.controller
 * @Author: zhouboyi
 * @Date: 2023/10/18 11:31
 * @Description: Qiniu 文件接口
 */
@Api(value = "Qiniu 文件接口")
@RestController
@RequestMapping(value = "/qiniu")
public class QiniuController {

    private final QiniuService qiniuService;

    @Autowired
    public QiniuController(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    @LogPrint(value = "文件上传")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储区域", name = "region", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件", name = "file", dataType = "MultipartFile", paramType = "form", required = true)
    })
    @PostMapping(value = "/upload/{region}/{bucket}")
    public ResponseEntity<String> upload(
        @PathVariable(value = "region") String region,
        @PathVariable(value = "bucket") String bucket,
        @RequestParam(value = "file") MultipartFile file
    ) throws Exception {
        String filepath = qiniuService.upload(region, bucket, file);
        return ResponseEntity.ok(filepath);
    }
}
