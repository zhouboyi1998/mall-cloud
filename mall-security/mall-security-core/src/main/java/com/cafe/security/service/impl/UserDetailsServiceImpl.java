package com.cafe.security.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.common.constant.HttpParameterConstant;
import com.cafe.common.enumeration.HttpStatusCodeEnum;
import com.cafe.security.model.UserInfo;
import com.cafe.user.feign.RoleFeign;
import com.cafe.user.feign.UserFeign;
import com.cafe.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2022/5/6 11:19
 * @Description: 用户详细信息加载类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final HttpServletRequest request;

    private final UserFeign userFeign;

    private final RoleFeign roleFeign;

    @Autowired
    public UserDetailsServiceImpl(HttpServletRequest request, UserFeign userFeign, RoleFeign roleFeign) {
        this.request = request;
        this.userFeign = userFeign;
        this.roleFeign = roleFeign;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从 Request Parameter 中获取客户端id
        String clientId = request.getParameter(HttpParameterConstant.CLIENT_ID_PARAMETER);

        // 查询用户信息
        User user = userFeign.detail(username, clientId).getBody();

        if (ObjectUtil.isNotNull(user)) {
            // 根据用户id查询角色名称列表
            List<String> roleNameList = roleFeign.listRoleName(user.getId()).getBody();
            if (ObjectUtil.isNull(roleNameList)) {
                throw new UsernameNotFoundException(HttpStatusCodeEnum.ROLE_NOT_FOUND.getMessage());
            }
            // 角色名称列表转换为数组形式
            String[] roleNameArray = roleNameList.toArray(new String[0]);
            UserInfo userDetails = new UserInfo(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(roleNameArray), user.getId());
            if (!userDetails.isEnabled()) {
                throw new DisabledException(HttpStatusCodeEnum.ACCOUNT_DISABLED.getMessage());
            } else if (!userDetails.isAccountNonLocked()) {
                throw new LockedException(HttpStatusCodeEnum.ACCOUNT_LOCKED.getMessage());
            } else if (!userDetails.isAccountNonExpired()) {
                throw new AccountExpiredException(HttpStatusCodeEnum.ACCOUNT_EXPIRED.getMessage());
            } else if (!userDetails.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException(HttpStatusCodeEnum.CREDENTIALS_EXPIRED.getMessage());
            }
            LOGGER.info("UserDetailsServiceImpl.loadUserByUsername(): username -> {}, client_id -> {}", username, clientId);
            return userDetails;
        } else {
            throw new UsernameNotFoundException(HttpStatusCodeEnum.USERNAME_NOT_FOUND.getMessage());
        }
    }
}
