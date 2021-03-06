package com.cafe.admin.feign;

import com.cafe.admin.bo.MenuRoleRelationBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.feign
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:53
 * @Description:
 */
@FeignClient(value = "mall-admin")
@RequestMapping(value = "/roleMenu")
public interface RoleMenuFeign {

    /**
     * 获取所有菜单路径和角色名称对应列表
     *
     * @return
     */
    @GetMapping(value = "/list/menuPath/roleName/bo")
    ResponseEntity<List<MenuRoleRelationBO>> listMenuRoleRelationBO();

    /**
     * 根据 菜单id列表 获取菜单路径和角色名称对应关系列表
     *
     * @param menuIds
     * @return
     */
    @PostMapping(value = "/list/menuPath/roleName/bo")
    ResponseEntity<List<MenuRoleRelationBO>> listMenuRoleRelationBO(@RequestBody List<Long> menuIds);
}
