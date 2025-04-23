package com.cafe.merchant.feign;

import com.cafe.merchant.model.entity.Shop;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.feign
 * @Author: zhouboyi
 * @Date: 2025/4/23 17:17
 * @Description:
 */
@FeignClient(value = "mall-merchant", contextId = "shop", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/shop")
public interface ShopFeign {

    /**
     * 根据店铺ids查询店铺列表
     *
     * @param ids 店铺id列表
     * @return 店铺列表
     */
    @PostMapping(value = "/list-by-ids")
    ResponseEntity<List<Shop>> listByIds(@RequestBody List<Long> ids);
}
