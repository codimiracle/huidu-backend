package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaperBookSalesVO {
    private Date date;
    private Integer quantity;
    private BigDecimal amount;

    public String getDateFormatted() {
        return DateFormatUtils.format(this.date, "MM-dd");
    }
}
