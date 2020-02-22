package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Commodity {
    /**
     * 购买项id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 购买项名称
     */
    private String name;

    /**
     * 购买项类型
     */
    private String type;

    /**
     * 购买项图片
     */
    private String picture;

    /**
     * 购买项介绍
     */
    private String introduction;

    /**
     * 评分
     */
    private String rate;

    /**
     * 重量(g)
     */
    private Double weight;

    /**
     * 初始库存
     */
    private String stock;

    /**
     * 可用库存
     */
    @Column(name = "available_stock")
    private String availableStock;

    /**
     * 销售数量
     */
    private String sales;

    /**
     * 运费
     */
    private String shipment;

    /**
     * 额外数据
     */
    private String extra;

    private String prices;

    private String status;

    /**
     * 获取购买项id
     *
     * @return id - 购买项id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置购买项id
     *
     * @param id 购买项id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取购买项名称
     *
     * @return name - 购买项名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置购买项名称
     *
     * @param name 购买项名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取购买项类型
     *
     * @return type - 购买项类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置购买项类型
     *
     * @param type 购买项类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取购买项图片
     *
     * @return picture - 购买项图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置购买项图片
     *
     * @param picture 购买项图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取购买项介绍
     *
     * @return introduction - 购买项介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置购买项介绍
     *
     * @param introduction 购买项介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取评分
     *
     * @return rate - 评分
     */
    public String getRate() {
        return rate;
    }

    /**
     * 设置评分
     *
     * @param rate 评分
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * 获取重量(g)
     *
     * @return weight - 重量(g)
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置重量(g)
     *
     * @param weight 重量(g)
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * 获取初始库存
     *
     * @return stock - 初始库存
     */
    public String getStock() {
        return stock;
    }

    /**
     * 设置初始库存
     *
     * @param stock 初始库存
     */
    public void setStock(String stock) {
        this.stock = stock;
    }

    /**
     * 获取可用库存
     *
     * @return available_stock - 可用库存
     */
    public String getAvailableStock() {
        return availableStock;
    }

    /**
     * 设置可用库存
     *
     * @param availableStock 可用库存
     */
    public void setAvailableStock(String availableStock) {
        this.availableStock = availableStock;
    }

    /**
     * 获取销售数量
     *
     * @return sales - 销售数量
     */
    public String getSales() {
        return sales;
    }

    /**
     * 设置销售数量
     *
     * @param sales 销售数量
     */
    public void setSales(String sales) {
        this.sales = sales;
    }

    /**
     * 获取运费
     *
     * @return shipment - 运费
     */
    public String getShipment() {
        return shipment;
    }

    /**
     * 设置运费
     *
     * @param shipment 运费
     */
    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

    /**
     * 获取额外数据
     *
     * @return extra - 额外数据
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 设置额外数据
     *
     * @param extra 额外数据
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * @return prices
     */
    public String getPrices() {
        return prices;
    }

    /**
     * @param prices
     */
    public void setPrices(String prices) {
        this.prices = prices;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}