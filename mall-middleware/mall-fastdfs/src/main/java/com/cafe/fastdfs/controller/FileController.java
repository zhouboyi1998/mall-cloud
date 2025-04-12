package com.cafe.fastdfs.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.fastdfs.model.vo.FileInfoVO;
import com.cafe.fastdfs.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.controller
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:35
 * @Description: FastDFS 文件接口
 */
@Api(value = "FastDFS 文件接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private final FileService fileService;

    @ApiLogPrint(value = "上传文件")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(value = "文件", name = "file", dataType = "MultipartFile", paramType = "form", required = true)
    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file) {
        String filepath = fileService.upload(file);
        return ResponseEntity.ok(filepath);
    }

    @ApiLogPrint(value = "下载文件")
    @ApiOperation(value = "下载文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "文件组", name = "group", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/download")
    public ResponseEntity<Void> download(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename,
        HttpServletResponse httpResponse
    ) {
        fileService.download(group, filename, httpResponse);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "删除文件")
    @ApiOperation(value = "删除文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "文件组", name = "group", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "query", required = true)
    })
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Integer> delete(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename
    ) {
        Integer count = fileService.delete(group, filename);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "获取文件信息")
    @ApiOperation(value = "获取文件信息")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "文件组", name = "group", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/info")
    public ResponseEntity<FileInfoVO> info(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename
    ) {
        FileInfoVO vo = fileService.info(group, filename);
        return ResponseEntity.ok(vo);
    }
}
