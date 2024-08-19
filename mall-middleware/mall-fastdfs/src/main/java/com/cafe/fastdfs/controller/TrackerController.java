package com.cafe.fastdfs.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.fastdfs.service.TrackerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.controller
 * @Author: zhouboyi
 * @Date: 2024/3/26 16:45
 * @Description: FastDFS 跟踪器接口
 */
@Api(value = "FastDFS 跟踪器接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tracker")
public class TrackerController {

    private final TrackerService trackerService;

    @ApiLogPrint(value = "获取跟踪器地址")
    @ApiOperation(value = "获取跟踪器地址")
    @GetMapping(value = "/url")
    public ResponseEntity<String> url() {
        String trackerUrl = trackerService.url();
        return ResponseEntity.ok(trackerUrl);
    }
}
