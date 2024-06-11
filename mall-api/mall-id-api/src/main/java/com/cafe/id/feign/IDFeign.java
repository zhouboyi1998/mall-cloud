package com.cafe.id.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.feign
 * @Author: zhouboyi
 * @Date: 2022/11/1 10:07
 * @Description:
 */
@FeignClient(value = "mall-id", contextId = "id", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/id")
public interface IDFeign {

    /**
     * 获取下一个分布式ID
     *
     * @return
     */
    @GetMapping(value = "/next")
    ResponseEntity<Long> nextId();
}
