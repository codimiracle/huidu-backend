package com.codimiracle.application.platform.huidu.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CartItemDTO {
    private String commodityId;
    private Integer quantity;
    private Date joinTime;
}
