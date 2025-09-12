package com.cafe.captcha.controller;

import com.cafe.captcha.model.query.CaptchaVerifyQuery;
import com.cafe.captcha.model.vo.Captcha;
import com.cafe.captcha.service.CaptchaServiceStrategyHolder;
import com.cafe.common.log.annotation.ApiLogPrint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.controller
 * @Author: zhouboyi
 * @Date: 2023/2/24 9:57
 * @Description: 图片验证码接口
 */
@Api(value = "图片验证码接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/captcha")
public class CaptchaController {

    private final CaptchaServiceStrategyHolder captchaServiceStrategyHolder;

    @ApiLogPrint(value = "获取图片验证码")
    @ApiOperation(value = "获取图片验证码")
    @ApiImplicitParam(value = "验证码生成器", name = "generator", dataType = "String", paramType = "query")
    @GetMapping(value = "/one")
    public ResponseEntity<Captcha> one(@RequestParam(value = "generator", required = false) String generator) {
        Captcha one = captchaServiceStrategyHolder.get(generator).one();
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "校验图片验证码")
    @ApiOperation(value = "校验图片验证码")
    @ApiImplicitParam(value = "校验图片验证码查询条件", name = "query", dataType = "CaptchaVerifyQuery", paramType = "body", required = true)
    @PostMapping(value = "/verify")
    public ResponseEntity<Boolean> verify(@RequestBody @Valid CaptchaVerifyQuery query) {
        Boolean verify = captchaServiceStrategyHolder.get().verify(query);
        return ResponseEntity.ok(verify);
    }
}
