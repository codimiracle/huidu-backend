package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user_token")
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "expire_time")
    private Date expireTime;

    private String token;

    @Transient
    private User user;
}