package com.cafe.security.model;

import com.cafe.common.constant.pool.StringConstant;
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
import java.util.Optional;
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

    private final Long id;

    private String username;

    private String password;

    private String mobile;

    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    public UserInfo(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(id, password, true, true, true, true, authorities);
        if (username == null || "".equals(username) || password == null) {
            throw new IllegalArgumentException("Username and password should not be null!");
        }
        this.username = username;
    }

    public UserInfo(Long id, String username, String mobile, String password, Collection<? extends GrantedAuthority> authorities) {
        // 使用手机号登录时也要同时保存用户名, 因为使用刷新令牌时需要用户名
        this(id, password, true, true, true, true, authorities);
        if (mobile == null || "".equals(mobile) || password == null) {
            throw new IllegalArgumentException("Mobile and password should not be null!");
        }
        this.username = username;
        this.mobile = mobile;
    }

    public UserInfo(Long id, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getMobile() {
        return this.mobile;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
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
    public boolean equals(Object rhs) {
        return rhs instanceof UserInfo && this.username.equals(((UserInfo) rhs).username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append(": ");
        builder.append("Id: ").append(this.id).append("; ");
        builder.append("Username: ").append(this.username).append("; ");
        builder.append("Password: [PROTECTED]; ");
        builder.append("Mobile: ").append(this.mobile).append("; ");
        builder.append("Enabled: ").append(this.enabled).append("; ");
        builder.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        builder.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        builder.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        if (!this.authorities.isEmpty()) {
            builder.append("Granted Authorities: ");
            boolean first = true;
            for (GrantedAuthority auth : this.authorities) {
                if (!first) {
                    builder.append(",");
                }
                first = false;
                builder.append(auth);
            }
        } else {
            builder.append("Not granted any authorities.");
        }
        return builder.toString();
    }

    public static UserInfo.UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static UserInfo.UserBuilder builder() {
        return new UserInfo.UserBuilder();
    }

    public static UserInfo.UserBuilder withUserDetails(UserDetails userDetails) {
        return withUsername(userDetails.getUsername()).password(userDetails.getPassword()).accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked()).authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired()).disabled(!userDetails.isEnabled());
    }

    public static class UserBuilder {

        private Long id;

        private String username;

        private String password;

        private String mobile;

        private List<GrantedAuthority> authorities;

        private boolean accountExpired;

        private boolean accountLocked;

        private boolean credentialsExpired;

        private boolean disabled;

        private Function<String, String> passwordEncoder;

        private UserBuilder() {
            this.passwordEncoder = (password) -> password;
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

        public UserInfo.UserBuilder password(String password) {
            Assert.notNull(password, "Password cannot be null!");
            this.password = password;
            return this;
        }

        public UserInfo.UserBuilder mobile(String mobile) {
            Assert.notNull(mobile, "Mobile cannot be null!");
            this.mobile = mobile;
            return this;
        }

        public UserInfo.UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "Encoder cannot be null!");
            this.passwordEncoder = encoder;
            return this;
        }

        public UserInfo.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"), () -> role + " cannot start with ROLE_ (it is automatically added)!");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return this.authorities(authorities);
        }

        public UserInfo.UserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities(Arrays.asList(authorities));
        }

        public UserInfo.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public UserInfo.UserBuilder authorities(String... authorities) {
            return this.authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        public UserInfo.UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public UserInfo.UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserInfo.UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public UserInfo.UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = (String) this.passwordEncoder.apply(this.password);
            String principal = Optional.ofNullable(this.username)
                .orElse(Optional.ofNullable(this.mobile)
                    .orElse(StringConstant.EMPTY));
            return new UserInfo(this.id, principal, encodedPassword, this.authorities);
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
}
