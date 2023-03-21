package com.cafe.security.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.cafe.common.constant.RequestConstant;
import com.cafe.common.enumeration.HttpStatusEnum;
import com.cafe.security.exception.MobileNotFoundException;
import com.cafe.security.model.UserInfo;
import com.cafe.security.service.UserDetailsExtensionService;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2022/5/6 11:19
 * @Description: 用户详细信息组装服务
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsExtensionService {

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
        String clientId = request.getParameter(RequestConstant.CLIENT_ID);

        // 根据用户名和客户端id查询用户详情
        User user = userFeign.detailByUsername(username, clientId).getBody();

        if (ObjectUtil.isNotNull(user)) {
            // 根据用户id查询角色名称列表
            List<String> roleNameList = roleFeign.listRoleName(user.getId()).getBody();
            if (ObjectUtil.isNull(roleNameList)) {
                throw new UsernameNotFoundException(HttpStatusEnum.ROLE_NOT_FOUND.getReasonPhrase());
            }
            // 角色名称列表转换为数组形式
            String[] roleNameArray = roleNameList.toArray(new String[0]);
            UserInfo userDetails = new UserInfo(user.getId(), user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(roleNameArray));
            // 校验用户状态
            validateUserDetails(userDetails);
            LOGGER.info("UserDetailsServiceImpl.loadUserByUsername(): username -> {}, client_id -> {}", username, clientId);
            return userDetails;
        } else {
            throw new UsernameNotFoundException(HttpStatusEnum.USERNAME_NOT_FOUND.getReasonPhrase());
        }
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws MobileNotFoundException {
        // 从 Request Parameter 中获取客户端id
        String clientId = request.getParameter(RequestConstant.CLIENT_ID);

        // 根据手机号和客户端id查询用户详情
        User user = userFeign.detailByMobile(mobile, clientId).getBody();

        if (ObjectUtil.isNotNull(user)) {
            // 根据用户id查询角色名称列表
            List<String> roleNameList = roleFeign.listRoleName(user.getId()).getBody();
            if (ObjectUtil.isNull(roleNameList)) {
                throw new UsernameNotFoundException(HttpStatusEnum.ROLE_NOT_FOUND.getReasonPhrase());
            }
            // 角色名称列表转换为数组形式
            String[] roleNameArray = roleNameList.toArray(new String[0]);
            UserInfo userDetails = new UserInfo(user.getId(), user.getUsername(), user.getMobile(), user.getPassword(), AuthorityUtils.createAuthorityList(roleNameArray));
            // 校验用户状态
            validateUserDetails(userDetails);
            LOGGER.info("UserDetailsServiceImpl.loadUserByMobile(): mobile -> {}, client_id -> {}", mobile, clientId);
            return userDetails;
        } else {
            throw new UsernameNotFoundException(HttpStatusEnum.USERNAME_NOT_FOUND.getReasonPhrase());
        }
    }

    /**
     * 校验用户状态
     *
     * @param userDetails
     */
    public void validateUserDetails(UserDetails userDetails) {
        if (!userDetails.isEnabled()) {
            throw new DisabledException(HttpStatusEnum.ACCOUNT_DISABLED.getReasonPhrase());
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException(HttpStatusEnum.ACCOUNT_LOCKED.getReasonPhrase());
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(HttpStatusEnum.ACCOUNT_EXPIRED.getReasonPhrase());
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(HttpStatusEnum.CREDENTIALS_EXPIRED.getReasonPhrase());
        }
    }
}
