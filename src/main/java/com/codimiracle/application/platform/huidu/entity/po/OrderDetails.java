package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;

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

    /**
     * 获取订单详情id
     *
     * @return id - 订单详情id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订单详情id
     *
     * @param id 订单详情id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品id
     *
     * @return commodity_id - 商品id
     */
    public Integer getCommodityId() {
        return commodityId;
    }

    /**
     * 设置商品id
     *
     * @param commodityId 商品id
     */
    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * 获取数量
     *
     * @return quantity - 数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置数量
     *
     * @param quantity 数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取小计
     *
     * @return prices - 小计
     */
    public Long getPrices() {
        return prices;
    }

    /**
     * 设置小计
     *
     * @param prices 小计
     */
    public void setPrices(Long prices) {
        this.prices = prices;
    }

    /**
     * @return order_number
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}