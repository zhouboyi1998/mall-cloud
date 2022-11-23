package com.cafe.user.feign;

import com.cafe.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.feign
 * @Author: zhouboyi
 * @Date: 2022/5/7 17:29
 * @Description:
 */
@FeignClient(value = "mall-user")
@RequestMapping(value = "/user")
public interface UserFeign {

    /**
     * 根据用户名和客户端id查询单个用户
     *
     * @param username
     * @param clientId
     * @return
     */
    @GetMapping(value = "/detail/{username}/{clientId}")
    ResponseEntity<User> detail(
        @PathVariable(value = "username") String username,
        @PathVariable(value = "clientId") String clientId
    );
}
