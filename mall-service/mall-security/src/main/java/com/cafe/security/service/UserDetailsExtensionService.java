package com.cafe.security.service;

import com.cafe.security.exception.EmailNotFoundException;
import com.cafe.security.exception.MobileNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service
 * @Author: zhouboyi
 * @Date: 2023/3/10 16:56
 * @Description: 用户详细信息加载扩展接口
 */
public interface UserDetailsExtensionService extends UserDetailsService {

    /**
     * 根据手机号组装详细信息
     *
     * @param mobile 手机号
     * @return
     * @throws MobileNotFoundException
     */
    UserDetails loadUserByMobile(String mobile) throws MobileNotFoundException;

    /**
     * 根据邮箱组装详细信息
     *
     * @param email
     * @return
     * @throws EmailNotFoundException
     */
    UserDetails loadUserByEmail(String email) throws EmailNotFoundException;
}
