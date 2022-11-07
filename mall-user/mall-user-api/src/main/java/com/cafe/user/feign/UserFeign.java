package com.cafe.user.feign;

import com.cafe.user.dto.UserDTO;
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
     * 根据用户名查询单个管理员
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/one/name/{username}")
    ResponseEntity<User> one(@PathVariable(value = "username") String username);

    /**
     * 根据用户名查询单个用户DTO
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/one/dto/name/{username}")
    ResponseEntity<UserDTO> oneDTO(@PathVariable(value = "username") String username);
}
