package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.UserInfoDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "user_info")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户性别（boy: 男, girl: 女）
     */
    private String gender;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 个人签名
     */
    private String slogan;

    /**
     * 个人介绍
     */
    private String introduction;

    /**
     * 生日
     */
    private Date birthdate;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户邮件
     */
    private String email;

    /**
     * 用户地区
     */
    private String region;

    public static UserInfo from(UserInfoDTO userInfoDTO) {
        if (Objects.isNull(userInfoDTO)) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        return userInfo;
    }
}