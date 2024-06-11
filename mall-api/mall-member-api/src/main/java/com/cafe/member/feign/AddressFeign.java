package com.cafe.member.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.member.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.feign
 * @Author: zhouboyi
 * @Date: 2023/10/25 16:53
 * @Description:
 */
@FeignClient(value = "mall-member", contextId = "address", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/address")
public interface AddressFeign {

    /**
     * 根据id查询单个收货地址
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/one/{id}")
    ResponseEntity<Address> one(@PathVariable(value = "id") Long id);
}
