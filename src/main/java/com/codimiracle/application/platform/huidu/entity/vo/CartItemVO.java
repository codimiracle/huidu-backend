package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CartItemVO {
    private String id;

    private String commodityId;
    private CommodityVO commodity;

    private String userId;

    private Integer quantity;

    private Date joinTime;
}
