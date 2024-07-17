package com.cafe.qiniu.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.qiniu.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiLogPrint(value = "上传文件")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件", name = "file", dataType = "MultipartFile", paramType = "form", required = true)
    })
    @PostMapping(value = "/upload/{bucket}")
    public ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        @RequestParam(value = "file") MultipartFile file
    ) throws Exception {
        String filepath = fileService.upload(bucket, file);
        return ResponseEntity.ok(filepath);
    }

    @ApiLogPrint(value = "删除文件")
    @ApiOperation(value = "删除文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "path", required = true)
    })
    @DeleteMapping(value = "/delete/{bucket}/{filename}")
    public ResponseEntity<Void> delete(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "filename") String filename
    ) throws Exception {
        fileService.delete(bucket, filename);
        return ResponseEntity.ok().build();
    }
}
