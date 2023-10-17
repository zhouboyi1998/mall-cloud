package com.cafe.user.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
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
@FeignClient(value = "mall-user", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/user")
public interface UserFeign {

    /**
     * 根据用户名和客户端id查询用户详情
     *
     * @param username
     * @param clientId
     * @return
     */
    @GetMapping(value = "/detail/username/{username}/{clientId}")
    ResponseEntity<User> detailByUsername(
        @PathVariable(value = "username") String username,
        @PathVariable(value = "clientId") String clientId
    );

    /**
     * 根据手机号和客户端id查询用户详情
     *
     * @param mobile
     * @param clientId
     * @return
     */
    @GetMapping(value = "/detail/mobile/{mobile}/{clientId}")
    ResponseEntity<User> detailByMobile(
        @PathVariable(value = "mobile") String mobile,
        @PathVariable(value = "clientId") String clientId
    );
}
