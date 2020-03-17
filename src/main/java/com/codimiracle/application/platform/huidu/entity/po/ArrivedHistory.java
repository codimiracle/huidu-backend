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
    private String userId;

}