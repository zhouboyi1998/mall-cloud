package com.cafe.id.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.id.service.IDServiceStrategyHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.controller
 * @Author: zhouboyi
 * @Date: 2022/10/31 16:57
 * @Description:
 */
@Api(value = "分布式ID生成接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/id")
public class IDController {

    private final IDServiceStrategyHolder idServiceStrategyHolder;

    @ApiLogPrint(value = "获取下一个分布式ID")
    @ApiOperation(value = "获取下一个分布式ID")
    @ApiImplicitParam(value = "分布式ID生成器", name = "generator", dataType = "String", paramType = "query")
    @GetMapping(value = "/next")
    public ResponseEntity<Long> nextId(@RequestParam(value = "generator", required = false) String generator) {
        Long id = idServiceStrategyHolder.get(generator).nextId();
        return ResponseEntity.ok(id);
    }
}
