package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;
import org.joda.money.Money;

@Data
public class PlatformDataVO {
    private Integer userCount;
    private Integer bookCount;
    private Integer orderCount;
    private Money orderAmount;
}
