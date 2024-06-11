package com.cafe.user.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.feign
 * @Author: zhouboyi
 * @Date: 2022/5/10 9:55
 * @Description:
 */
@FeignClient(value = "mall-user", contextId = "role", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/role")
public interface RoleFeign {

    /**
     * 根据用户id查询角色名称列表
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/list/name/{userId}")
    ResponseEntity<List<String>> listRoleName(@PathVariable(value = "userId") Long userId);
}
