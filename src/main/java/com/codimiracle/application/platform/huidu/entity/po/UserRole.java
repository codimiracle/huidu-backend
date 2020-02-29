package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.UserRoleDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Data
@Table(name = "user_role")
public class UserRole {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 权限标识
     */
    private List<String> authorities;

    private boolean deleted;

    public static UserRole from(UserRoleDTO userRoleDTO) {
        if (Objects.isNull(userRoleDTO)) {
            return null;
        }
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRoleDTO, userRole);
        return userRole;
    }
}