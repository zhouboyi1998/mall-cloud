package com.cafe.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.feign
 * @Author: zhouboyi
 * @Date: 2022/5/10 9:55
 * @Description:
 */
@FeignClient(value = "mall-admin")
@RequestMapping(value = "/role")
public interface RoleFeign {

    /**
     * 根据管理员id查询角色名称列表
     *
     * @param adminId 管理员id
     * @return
     */
    @GetMapping(value = "/list/name/{adminId}")
    ResponseEntity<List<String>> listRoleName(@PathVariable(value = "adminId") Long adminId);
}
