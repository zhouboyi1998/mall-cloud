package com.cafe.admin.feign;

import com.cafe.admin.model.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.feign
 * @Author: zhouboyi
 * @Date: 2022/5/7 17:29
 * @Description:
 */
@FeignClient(value = "mall-admin")
@RequestMapping(value = "/admin")
public interface AdminFeign {

    /**
     * 根据管理员用户名查询单个管理员
     *
     * @param adminName
     * @return
     */
    @GetMapping(value = "/one/name/{adminName}")
    ResponseEntity<Admin> one(@PathVariable(value = "adminName") String adminName);
}
