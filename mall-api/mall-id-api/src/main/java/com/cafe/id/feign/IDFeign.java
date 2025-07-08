package com.cafe.id.feign;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.feign
 * @Author: zhouboyi
 * @Date: 2022/11/1 10:07
 * @Description:
 */
@FeignClient(value = ServiceConstant.MALL_ID, contextId = "id", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/id")
public interface IDFeign {

    /**
     * 获取下一个分布式ID
     *
     * @param generator 分布式ID生成器
     * @return 分布式ID
     */
    @GetMapping(value = "/next")
    ResponseEntity<Long> nextId(@RequestParam(value = "generator", required = false) String generator);
}
