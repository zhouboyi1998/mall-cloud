package com.cafe.user.feign;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import com.cafe.user.model.entity.User;
import com.cafe.user.model.vo.UserVO;
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
@FeignClient(value = ServiceConstant.MALL_USER, contextId = "user", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/user")
public interface UserFeign {

    /**
     * 根据客户端id和用户查询条件查询用户信息
     *
     * @param clientId 客户端id
     * @param user     用户查询条件
     * @return 用户信息
     */
    @PostMapping(value = "/detail/{clientId}")
    ResponseEntity<UserVO> detail(@PathVariable(value = "clientId") String clientId, @RequestBody User user);
}
