package com.cafe.user.feign;

import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.feign
 * @Author: zhouboyi
 * @Date: 2024/12/7 4:09
 * @Description:
 */
@FeignClient(value = "mall-user", contextId = "role-resource", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/role-resource")
public interface RoleResourceFeign {

}
