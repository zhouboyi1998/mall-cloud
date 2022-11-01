package com.cafe.id.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.id.service.IDService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.controller
 * @Author: zhouboyi
 * @Date: 2022/10/31 16:57
 * @Description:
 */
@Api(value = "分布式ID生成接口")
@RestController
@RequestMapping(value = "/id")
public class IDController {

    private IDService idService;

    @Autowired
    public IDController(@Qualifier(value = "SnowflakeIDServiceImpl") IDService idService) {
        this.idService = idService;
    }

    @LogPrint(value = "获取下一个分布式ID")
    @ApiOperation(value = "获取下一个分布式ID")
    @GetMapping(value = "/next")
    public ResponseEntity<Long> nextId() {
        Long id = idService.nextId();
        return ResponseEntity.ok(id);
    }
}
