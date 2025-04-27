package com.cafe.security.userdetails;

import com.cafe.security.exception.EmailNotFoundException;
import com.cafe.security.exception.MobileNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.userdetails
 * @Author: zhouboyi
 * @Date: 2023/3/10 16:56
 * @Description: 多主体类型的用户详细信息加载接口 (主体类型: username、mobile、email)
 */
public interface MultiPrincipalUserDetailsService extends UserDetailsService {

    /**
     * 根据手机号组装用户详细信息
     *
     * @param mobile 手机号
     * @return 用户详细信息
     * @throws MobileNotFoundException 手机号对应用户不存在异常
     */
    UserDetails loadUserByMobile(String mobile) throws MobileNotFoundException;

    /**
     * 根据邮箱组装用户详细信息
     *
     * @param email 邮箱
     * @return 用户详细信息
     * @throws EmailNotFoundException 邮箱对应用户不存在异常
     */
    UserDetails loadUserByEmail(String email) throws EmailNotFoundException;
}
