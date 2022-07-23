package com.cafe.file.fastdfs.controller;

import com.cafe.file.fastdfs.service.FastdfsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.controller
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:35
 * @Description:
 */
@RestController
@RequestMapping(value = "fastdfs")
public class FastdfsController {

    private FastdfsService fastdfsService;

    @Autowired
    public FastdfsController(FastdfsService fastdfsService) {
        this.fastdfsService = fastdfsService;
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "文件上传")
    @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form", dataType = "MultipartFile")
    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        // 将文件上传到 FastDFS 中
        String[] values = fastdfsService.upload(file);
        String url = fastdfsService.getTrackerUrl() + "/" + values[0] + "/" + values[1];
        return ResponseEntity.ok(url);
    }
}
