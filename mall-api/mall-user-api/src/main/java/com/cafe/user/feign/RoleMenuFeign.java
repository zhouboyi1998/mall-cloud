package com.cafe.user.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.feign
 * @Author: zhouboyi
 * @Date: 2024/12/7 4:09
 * @Description:
 */
@FeignClient(value = "mall-user", contextId = "role-menu", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/role-menu")
public interface RoleMenuFeign {

    /**
     * 根据菜单路径获取角色名称列表
     *
     * @param menuPath 菜单路径
     * @return 角色名称列表
     */
    @GetMapping(value = "/role-name-list")
    ResponseEntity<List<String>> roleNameList(@RequestParam(value = "menuPath") String menuPath);
}
