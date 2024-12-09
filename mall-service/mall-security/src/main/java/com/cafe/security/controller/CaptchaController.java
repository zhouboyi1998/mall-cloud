package com.cafe.security.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.security.captcha.CaptchaServiceStrategy;
import com.cafe.security.model.vo.Captcha;
import com.cafe.security.property.CaptchaProperties;
import com.cafe.security.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.controller
 * @Author: zhouboyi
 * @Date: 2023/2/24 9:57
 * @Description: 图片验证码接口
 */
@Api(value = "图片验证码接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/captcha")
public class CaptchaController {

    private final CaptchaServiceStrategy captchaServiceStrategy;

    private final CaptchaProperties captchaProperties;

    private CaptchaService captchaService() {
        return captchaServiceStrategy.captchaService(captchaProperties.getService().getImplementation());
    }

    @ApiLogPrint(value = "获取图片验证码")
    @ApiOperation(value = "获取图片验证码")
    @GetMapping(value = "/one")
    public ResponseEntity<Captcha> one() {
        Captcha one = captchaService().one();
        return ResponseEntity.ok(one);
    }
}
