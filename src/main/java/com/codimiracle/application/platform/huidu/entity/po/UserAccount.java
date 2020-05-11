package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.typehandler.MoneyTypeHandler;
import lombok.Data;
import org.joda.money.Money;
import tk.mybatis.mapper.annotation.ColumnType;

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
    @ColumnType(typeHandler = MoneyTypeHandler.class)
    private Money balance;

    private Integer version;
}