package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;
import org.joda.money.Money;

import javax.persistence.*;

@Data
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 荟币余额
     */
    private Money balance;

    private Integer version;
}