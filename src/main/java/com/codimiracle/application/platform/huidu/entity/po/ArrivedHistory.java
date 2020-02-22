package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "arrived_history")
public class ArrivedHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 连续签到天数
     */
    private Integer days;

    /**
     * 今天名言
     */
    private String motto;

    /**
     * 签到时间
     */
    @Column(name = "sign_time")
    private Date signTime;

    /**
     * 签到用户
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取连续签到天数
     *
     * @return days - 连续签到天数
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 设置连续签到天数
     *
     * @param days 连续签到天数
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 获取今天名言
     *
     * @return motto - 今天名言
     */
    public String getMotto() {
        return motto;
    }

    /**
     * 设置今天名言
     *
     * @param motto 今天名言
     */
    public void setMotto(String motto) {
        this.motto = motto;
    }

    /**
     * 获取签到时间
     *
     * @return sign_time - 签到时间
     */
    public Date getSignTime() {
        return signTime;
    }

    /**
     * 设置签到时间
     *
     * @param signTime 签到时间
     */
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    /**
     * 获取签到用户
     *
     * @return user_id - 签到用户
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置签到用户
     *
     * @param userId 签到用户
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}