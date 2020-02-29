package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class User implements UserDetails {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 账号过期时间
     */
    @Column(name = "account_expired")
    private Date accountExpired;

    /**
     * 登录凭据过期时间
     */
    @Column(name = "credentials_expired")
    private Date credentialsExpired;

    /**
     * 账号是否锁定(1: 锁定, 0: 未锁定)
     */
    @Column(name = "account_locked")
    private boolean accountLocked;

    /**
     * 账号是否禁用(1: 可用, 0: 不可用)
     */
    private boolean enabled;

    /**
     * 账号已经删除
     */
    private boolean deleted;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private String roleId;

    @Transient
    private UserRole role;

    @Transient
    private UserInfo extra;

    public static User from(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        UserInfo userInfo = UserInfo.from(userDTO.getExtra());
        user.setExtra(userInfo);
        return user;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.isNull(role) || Objects.isNull(role.getAuthorities())) {
            return Collections.emptyList();
        }
        return role.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return Objects.isNull(this.accountExpired) || this.accountExpired.after(new Date());
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return Objects.isNull(this.credentialsExpired) || this.credentialsExpired.after(new Date());
    }
}