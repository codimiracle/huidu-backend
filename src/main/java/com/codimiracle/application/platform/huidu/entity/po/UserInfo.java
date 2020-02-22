package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;
import java.util.Date;

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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户性别（boy: 男, girl: 女）
     *
     * @return gender - 用户性别（boy: 男, girl: 女）
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置用户性别（boy: 男, girl: 女）
     *
     * @param gender 用户性别（boy: 男, girl: 女）
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取用户年龄
     *
     * @return age - 用户年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置用户年龄
     *
     * @param age 用户年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取个人签名
     *
     * @return slogan - 个人签名
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * 设置个人签名
     *
     * @param slogan 个人签名
     */
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    /**
     * 获取个人介绍
     *
     * @return introduction - 个人介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置个人介绍
     *
     * @param introduction 个人介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取生日
     *
     * @return birthdate - 生日
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * 设置生日
     *
     * @param birthdate 生日
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * 获取用户电话
     *
     * @return phone - 用户电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户电话
     *
     * @param phone 用户电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户邮件
     *
     * @return email - 用户邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮件
     *
     * @param email 用户邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户地区
     *
     * @return region - 用户地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置用户地区
     *
     * @param region 用户地区
     */
    public void setRegion(String region) {
        this.region = region;
    }
}