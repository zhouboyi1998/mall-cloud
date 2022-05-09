package com.cafe.security.management.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.admin.feign.AdminFeign;
import com.cafe.admin.model.Admin;
import com.cafe.common.security.constant.ExceptionMessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.service.impl
 * @Author: zhouboyi
 * @Date: 2022/5/6 11:19
 * @Description: Admin 详细信息加载类
 */
@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    private AdminFeign adminFeign;

    @Autowired
    public AdminDetailsServiceImpl(AdminFeign adminFeign) {
        this.adminFeign = adminFeign;
    }

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        Admin admin = adminFeign.one(adminName).getBody();

        if (ObjectUtil.isNotNull(admin)) {
            User userDetails = new User(admin.getAdminName(), admin.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
            if (!userDetails.isEnabled()) {
                throw new DisabledException(ExceptionMessageEnum.ACCOUNT_DISABLED.getMessage());
            } else if (!userDetails.isAccountNonLocked()) {
                throw new LockedException(ExceptionMessageEnum.ACCOUNT_LOCKED.getMessage());
            } else if (!userDetails.isAccountNonExpired()) {
                throw new AccountExpiredException(ExceptionMessageEnum.ACCOUNT_EXPIRED.getMessage());
            } else if (!userDetails.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException(ExceptionMessageEnum.CREDENTIALS_EXPIRED.getMessage());
            }
            return userDetails;
        } else {
            throw new UsernameNotFoundException(ExceptionMessageEnum.USERNAME_NOTFOUND.getMessage());
        }
    }
}
