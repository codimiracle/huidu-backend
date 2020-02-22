package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;

@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 荟币余额
     */
    private Long balance;

    private Integer version;

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
     * 获取荟币余额
     *
     * @return balance - 荟币余额
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * 设置荟币余额
     *
     * @param balance 荟币余额
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}