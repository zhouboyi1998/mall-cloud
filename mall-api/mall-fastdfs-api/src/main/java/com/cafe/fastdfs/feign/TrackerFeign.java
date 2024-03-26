package com.cafe.fastdfs.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.feign
 * @Author: zhouboyi
 * @Date: 2024/3/26 21:08
 * @Description:
 */
@FeignClient(value = "mall-fastdfs", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/tracker")
public interface TrackerFeign {

    /**
     * 获取跟踪器地址
     *
     * @return
     */
    @GetMapping(value = "/url")
    ResponseEntity<String> url();
}
