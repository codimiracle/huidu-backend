package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.util.StringifizationUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;

@Data
public class User implements UserDetails{
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    private UserRole role;

    @JsonDeserialize
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return StringifizationUtil.toSet(role.getAuthorities(), SimpleGrantedAuthority::new);
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountExpired.after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpired.after(new Date());
    }
}