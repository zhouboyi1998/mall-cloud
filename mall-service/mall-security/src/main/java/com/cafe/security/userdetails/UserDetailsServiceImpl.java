package com.cafe.security.userdetails;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.security.exception.EmailNotFoundException;
import com.cafe.security.exception.MobileNotFoundException;
import com.cafe.security.model.bo.UserInfo;
import com.cafe.user.feign.RoleFeign;
import com.cafe.user.feign.UserFeign;
import com.cafe.user.model.entity.User;
import com.cafe.user.model.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.userdetails
 * @Author: zhouboyi
 * @Date: 2022/5/6 11:19
 * @Description: 用户详细信息加载实现类
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements MultiPrincipalUserDetailsService {

    private final HttpServletRequest request;

    private final UserFeign userFeign;

    private final RoleFeign roleFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从请求参数中获取客户端id
        String clientId = request.getParameter(RequestConstant.Parameter.CLIENT_ID);

        // 根据客户端id和用户名查询用户
        UserVO userVO = Optional.ofNullable(userFeign.detail(clientId, new User().setUsername(username)))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new UsernameNotFoundException(HttpStatusEnum.USERNAME_NOT_FOUND.getReasonPhrase()));

        // 根据用户id查询角色名称列表
        String[] roleNameArray = Optional.ofNullable(roleFeign.nameList(userVO.getId()))
            .map(ResponseEntity::getBody)
            .map(roleNameList -> roleNameList.toArray(new String[0]))
            .orElseThrow(() -> new UsernameNotFoundException(HttpStatusEnum.ROLE_UNASSIGNED.getReasonPhrase()));

        // 新建用户详细信息
        UserInfo userDetails = new UserInfo(UserInfo.PrincipalType.USERNAME, username, userVO.getId(), userVO.getUsername(), userVO.getPassword(), userVO.getStatus(), AuthorityUtils.createAuthorityList(roleNameArray));
        // 校验用户状态
        validateUserDetails(userDetails);
        log.info("UserDetailsServiceImpl.loadUserByUsername(): client id -> {}, username -> {}", clientId, username);
        return userDetails;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws MobileNotFoundException {
        // 从请求参数中获取客户端id
        String clientId = request.getParameter(RequestConstant.Parameter.CLIENT_ID);

        // 根据客户端id和手机号查询用户
        UserVO userVO = Optional.ofNullable(userFeign.detail(clientId, new User().setMobile(mobile)))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new MobileNotFoundException(HttpStatusEnum.MOBILE_NOT_FOUND.getReasonPhrase()));

        // 根据用户id查询角色名称列表
        String[] roleNameArray = Optional.ofNullable(roleFeign.nameList(userVO.getId()))
            .map(ResponseEntity::getBody)
            .map(roleNameList -> roleNameList.toArray(new String[0]))
            .orElseThrow(() -> new MobileNotFoundException(HttpStatusEnum.ROLE_UNASSIGNED.getReasonPhrase()));

        // 新建用户详细信息
        UserInfo userDetails = new UserInfo(UserInfo.PrincipalType.MOBILE, mobile, userVO.getId(), userVO.getUsername(), userVO.getPassword(), userVO.getStatus(), AuthorityUtils.createAuthorityList(roleNameArray));
        // 校验用户状态
        validateUserDetails(userDetails);
        log.info("UserDetailsServiceImpl.loadUserByMobile(): client id -> {}, mobile -> {}", clientId, mobile);
        return userDetails;
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        // 从请求参数中获取客户端id
        String clientId = request.getParameter(RequestConstant.Parameter.CLIENT_ID);

        // 根据客户端id和邮箱查询用户
        UserVO userVO = Optional.ofNullable(userFeign.detail(clientId, new User().setEmail(email)))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new EmailNotFoundException(HttpStatusEnum.EMAIL_NOT_FOUND.getReasonPhrase()));

        // 根据用户id查询角色名称列表
        String[] roleNameArray = Optional.ofNullable(roleFeign.nameList(userVO.getId()))
            .map(ResponseEntity::getBody)
            .map(roleNameList -> roleNameList.toArray(new String[0]))
            .orElseThrow(() -> new EmailNotFoundException(HttpStatusEnum.ROLE_UNASSIGNED.getReasonPhrase()));

        // 新建用户详细信息
        UserInfo userDetails = new UserInfo(UserInfo.PrincipalType.EMAIL, email, userVO.getId(), userVO.getUsername(), userVO.getPassword(), userVO.getStatus(), AuthorityUtils.createAuthorityList(roleNameArray));
        // 校验用户状态
        validateUserDetails(userDetails);
        log.info("UserDetailsServiceImpl.loadUserByEmail(): client id -> {}, email -> {}", clientId, email);
        return userDetails;
    }

    /**
     * 校验用户状态
     *
     * @param userDetails 用户详细信息
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
