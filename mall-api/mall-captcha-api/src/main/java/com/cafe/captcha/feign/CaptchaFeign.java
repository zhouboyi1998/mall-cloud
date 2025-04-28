package com.cafe.captcha.feign;

import com.cafe.captcha.model.query.CaptchaVerifyQuery;
import com.cafe.captcha.model.vo.Captcha;
import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.feign
 * @Author: zhouboyi
 * @Date: 2025/4/28 17:41
 * @Description:
 */
@FeignClient(value = ServiceConstant.MALL_CAPTCHA, contextId = "captcha", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/captcha")
public interface CaptchaFeign {

    /**
     * 获取图片验证码
     *
     * @param generator 验证码生成器
     * @return 图片验证码
     */
    @GetMapping(value = "/one")
    ResponseEntity<Captcha> one(@RequestParam(value = "generator", required = false) String generator);

    /**
     * 校验图片验证码
     *
     * @param query 校验图片验证码查询条件
     * @return 校验结果
     */
    @PostMapping(value = "/verify")
    ResponseEntity<Boolean> verify(@RequestBody CaptchaVerifyQuery query);
}
