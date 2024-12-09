package com.cafe.user.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.user.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.feign
 * @Author: zhouboyi
 * @Date: 2022/5/7 17:29
 * @Description:
 */
@FeignClient(value = "mall-user", contextId = "user", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/user")
public interface UserFeign {

    /**
     * 根据客户端id和用户信息查询用户
     *
     * @param clientId
     * @param user
     * @return
     */
    @PostMapping(value = "/detail/{clientId}")
    ResponseEntity<User> detail(@PathVariable(value = "clientId") String clientId, @RequestBody User user);
}
