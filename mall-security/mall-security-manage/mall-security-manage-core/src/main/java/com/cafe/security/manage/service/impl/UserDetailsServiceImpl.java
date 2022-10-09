package com.cafe.security.manage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.admin.feign.AdminFeign;
import com.cafe.admin.feign.RoleFeign;
import com.cafe.admin.model.Admin;
import com.cafe.common.enumeration.HttpStatusCodeEnum;
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

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.service.impl
 * @Author: zhouboyi
 * @Date: 2022/5/6 11:19
 * @Description: 管理员账号详细信息加载类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AdminFeign adminFeign;

    private RoleFeign roleFeign;

    @Autowired
    public UserDetailsServiceImpl(AdminFeign adminFeign, RoleFeign roleFeign) {
        this.adminFeign = adminFeign;
        this.roleFeign = roleFeign;
    }

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        // 查询管理员信息
        Admin admin = adminFeign.one(adminName).getBody();
        // 根据管理员id查询角色名称列表
        List<String> roleNameList = roleFeign.listRoleNameByAdminId(admin.getId()).getBody();
        // 角色名称列表转换为数组形式
        String[] roleNameArray = roleNameList.toArray(new String[roleNameList.size()]);

        if (ObjectUtil.isNotNull(admin)) {
            User userDetails = new User(admin.getAdminName(), admin.getPassword(), AuthorityUtils.createAuthorityList(roleNameArray));
            if (!userDetails.isEnabled()) {
                throw new DisabledException(HttpStatusCodeEnum.ACCOUNT_DISABLED.getMessage());
            } else if (!userDetails.isAccountNonLocked()) {
                throw new LockedException(HttpStatusCodeEnum.ACCOUNT_LOCKED.getMessage());
            } else if (!userDetails.isAccountNonExpired()) {
                throw new AccountExpiredException(HttpStatusCodeEnum.ACCOUNT_EXPIRED.getMessage());
            } else if (!userDetails.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException(HttpStatusCodeEnum.CREDENTIALS_EXPIRED.getMessage());
            }
            return userDetails;
        } else {
            throw new UsernameNotFoundException(HttpStatusCodeEnum.USERNAME_NOT_FOUND.getMessage());
        }
    }
}
