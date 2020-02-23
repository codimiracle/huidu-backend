package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "order_details")
public class OrderDetails {
    /**
     * 订单详情id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 小计
     */
    private Long prices;

    @Column(name = "order_number")
    private Integer orderNumber;
}