package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

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
}