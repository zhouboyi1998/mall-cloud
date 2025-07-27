package com.cafe.foundation.feign;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.feign
 * @Author: zhouboyi
 * @Date: 2025/7/27 23:07
 * @Description:
 */
@FeignClient(value = ServiceConstant.MALL_FOUNDATION, contextId = "sensitive", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/sensitive")
public interface SensitiveFeign {

    /**
     * 匹配文本中的所有敏感词
     *
     * @param text 被校验的文本
     * @return 敏感词列表
     */
    @PostMapping(value = "/match-text")
    ResponseEntity<List<String>> matchText(@RequestBody String text);

    /**
     * 校验文本中是否包含敏感词
     *
     * @param text 被校验的文本
     * @return 是否包含敏感词
     */
    @PostMapping(value = "/validate-text")
    ResponseEntity<Boolean> validateText(@RequestBody String text);
}
