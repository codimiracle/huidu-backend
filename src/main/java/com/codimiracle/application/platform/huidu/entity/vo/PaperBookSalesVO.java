package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;
import org.joda.money.Money;

import java.util.Date;

@Data
public class PaperBookSalesVO {
    private Date date;
    private Integer quantity;
    private Money amount;
}
