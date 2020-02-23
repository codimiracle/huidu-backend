package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
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
    /**
     * @param id
     */
    /**
     * 获取连续签到天数
     *
     * @return days - 连续签到天数
     */
    /**
     * 设置连续签到天数
     *
     * @param days 连续签到天数
     */
    /**
     * 获取今天名言
     *
     * @return motto - 今天名言
     */
    /**
     * 设置今天名言
     *
     * @param motto 今天名言
     */
    /**
     * 获取签到时间
     *
     * @return sign_time - 签到时间
     */
    /**
     * 设置签到时间
     *
     * @param signTime 签到时间
     */
    /**
     * 获取签到用户
     *
     * @return user_id - 签到用户
     */
    /**
     * 设置签到用户
     *
     * @param userId 签到用户
     */
}