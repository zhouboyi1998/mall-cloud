/*
 * Reference Code Source
 *
 * @Source: https://github.com/spring-projects/spring-security
 * @GroupId: org.springframework.security
 * @ArtifactId: spring-security-core
 * @Version: 5.3.9.RELEASE
 * @Package: org.springframework.security.core.userdetails
 * @Class: User.java
 */
package com.cafe.security.model;

import com.cafe.common.util.json.JacksonUtil;
import io.swagger.annotations.ApiModel;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.model
 * @Author: zhouboyi
 * @Date: 2022/5/6 14:42
 * @Description: 用户详细信息
 */
@ApiModel(value = "UserInfo", description = "用户详细信息")
public class UserInfo implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private final Long id;

    /**
     * 用户名
     */
    private final String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号是否启用
     */
    private final boolean enabled;

    /**
     * 账号是否未锁定
     */
    private final boolean accountNonLocked;

    /**
     * 账号是否未过期
     */
    private final boolean accountNonExpired;

    /**
     * 凭证是否未过期
     */
    private final boolean credentialsNonExpired;

    /**
     * 权限列表 (去重)
     */
    private final Set<GrantedAuthority> authorities;

    public UserInfo(PrincipalType principalType, String principal, Long id, String username, String password, Integer status, Collection<? extends GrantedAuthority> authorities) {
        this(principalType, principal, id, username, password, Status.isEnable(status), true, true, true, authorities);
    }

    public UserInfo(PrincipalType principalType, String principal, Long id, String username, String password, boolean enabled, boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired, Collection<? extends GrantedAuthority> authorities) {
        if (principal == null || "".equals(principal) || password == null) {
            throw new IllegalArgumentException("Principal and password cannot be null!");
        }

        switch (principalType) {
            case USERNAME:
                break;
            case MOBILE:
                this.mobile = principal;
                break;
            case EMAIL:
                this.email = principal;
                break;
            default:
                throw new IllegalArgumentException("Invalid principal type: " + principalType);
        }

        this.id = id;
        // 不管使用什么作为账号登录, 都要同时保存用户名, 因为使用刷新令牌获取新的访问令牌时, 需要用到用户名
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection!");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new UserInfo.AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements!");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UserDetails && Objects.equals(this.username, ((UserDetails) other).getUsername());
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public String toString() {
        return JacksonUtil.writeValueAsString(this);
    }

    public static UserInfo.UserBuilder builder() {
        return new UserInfo.UserBuilder();
    }

    public static UserInfo.UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static UserInfo.UserBuilder withUserDetails(UserDetails userDetails) {
        return withUsername(userDetails.getUsername())
            .password(userDetails.getPassword())
            .disabled(!userDetails.isEnabled())
            .accountLocked(!userDetails.isAccountNonLocked())
            .accountExpired(!userDetails.isAccountNonExpired())
            .credentialsExpired(!userDetails.isCredentialsNonExpired())
            .authorities(userDetails.getAuthorities());
    }

    public static UserInfo.UserBuilder withUserInfo(UserInfo userInfo) {
        return withUserDetails(userInfo)
            .id(userInfo.getId())
            .mobile(userInfo.getMobile())
            .email(userInfo.getEmail());
    }

    public static class UserBuilder {

        private PrincipalType principalType;

        /**
         * 用户ID
         */
        private Long id;

        /**
         * 用户名
         */
        private String username;

        /**
         * 手机号
         */
        private String mobile;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 密码
         */
        private String password;

        /**
         * 账户是否禁用
         */
        private boolean disabled;

        /**
         * 账户是否锁定
         */
        private boolean accountLocked;

        /**
         * 账户是否过期
         */
        private boolean accountExpired;

        /**
         * 证书是否过期
         */
        private boolean credentialsExpired;

        /**
         * 权限列表
         */
        private List<GrantedAuthority> authorities;

        private Function<String, String> passwordEncoder = password -> password;

        private UserBuilder() {

        }

        public UserInfo.UserBuilder principalType(PrincipalType principalType) {
            Assert.notNull(principalType, "Principal type cannot be null!");
            this.principalType = principalType;
            return this;
        }

        public UserInfo.UserBuilder id(Long id) {
            Assert.notNull(id, "Id cannot be null!");
            this.id = id;
            return this;
        }

        public UserInfo.UserBuilder username(String username) {
            Assert.notNull(username, "Username cannot be null!");
            this.username = username;
            return this;
        }

        public UserInfo.UserBuilder mobile(String mobile) {
            Assert.notNull(mobile, "Mobile cannot be null!");
            this.mobile = mobile;
            return this;
        }

        public UserInfo.UserBuilder email(String email) {
            Assert.notNull(email, "Email cannot be null!");
            this.email = email;
            return this;
        }

        public UserInfo.UserBuilder password(String password) {
            Assert.notNull(password, "Password cannot be null!");
            this.password = password;
            return this;
        }

        public UserInfo.UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserInfo.UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserInfo.UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public UserInfo.UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public UserInfo.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public UserInfo.UserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities(Arrays.asList(authorities));
        }

        public UserInfo.UserBuilder authorities(String... authorities) {
            return this.authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        public UserInfo.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"), () -> role + " cannot start with ROLE_ (it is automatically added)!");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return this.authorities(authorities);
        }

        public UserInfo.UserBuilder passwordEncoder(Function<String, String> passwordEncoder) {
            Assert.notNull(passwordEncoder, "PasswordEncoder cannot be null!");
            this.passwordEncoder = passwordEncoder;
            return this;
        }

        public UserDetails build() {
            String principal;
            switch (this.principalType) {
                case USERNAME:
                    principal = this.username;
                    break;
                case MOBILE:
                    principal = this.mobile;
                    break;
                case EMAIL:
                    principal = this.email;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid principal type: " + this.principalType);
            }
            String encodedPassword = this.passwordEncoder.apply(this.password);
            return new UserInfo(this.principalType, principal, this.id, this.username, encodedPassword, !this.disabled, !this.accountLocked, !this.accountExpired, !this.credentialsExpired, this.authorities);
        }
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = 1L;

        private AuthorityComparator() {

        }

        @Override
        public int compare(GrantedAuthority authority1, GrantedAuthority authority2) {
            if (authority2.getAuthority() == null) {
                return -1;
            } else {
                return authority1.getAuthority() == null ? 1 : authority1.getAuthority().compareTo(authority2.getAuthority());
            }
        }
    }

    public enum PrincipalType {

        /**
         * 用户名
         */
        USERNAME,

        /**
         * 手机号
         */
        MOBILE,

        /**
         * 邮箱
         */
        EMAIL
    }

    public enum Status {

        /**
         * 启用
         */
        ENABLE(1),

        /**
         * 禁用
         */
        DISABLE(0);

        /**
         * 状态码
         */
        private final Integer code;

        Status(Integer code) {
            this.code = code;
        }

        public Integer code() {
            return this.code;
        }

        public static Boolean isEnable(Integer code) {
            return ENABLE.code().equals(code);
        }
    }
}
