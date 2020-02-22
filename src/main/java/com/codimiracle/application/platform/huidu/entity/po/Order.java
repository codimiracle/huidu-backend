package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import java.util.Date;

public class Order {
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 订单所有者id
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）
     */
    private String type;

    /**
     * 支付类型（wechat: 微信, alipay: 支付宝）
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 收货地址
     */
    @Column(name = "address_id")
    private Integer addressId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 发货时间
     */
    @Column(name = "deliver_time")
    private Date deliverTime;

    /**
     * 交易完成时间
     */
    @Column(name = "closing_time")
    private Date closingTime;

    /**
     * 总金额
     */
    @Column(name = "total_money")
    private String totalMoney;

    /**
     * 运费
     */
    @Column(name = "shipment_money")
    private String shipmentMoney;

    /**
     * 订单状态（）
     */
    private String status;

    /**
     * 物流信息
     */
    @Column(name = "logistics_information")
    private String logisticsInformation;

    /**
     * @return order_number
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 获取订单所有者id
     *
     * @return owner_id - 订单所有者id
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 设置订单所有者id
     *
     * @param ownerId 订单所有者id
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）
     *
     * @return type - 订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）
     *
     * @param type 订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取支付类型（wechat: 微信, alipay: 支付宝）
     *
     * @return pay_type - 支付类型（wechat: 微信, alipay: 支付宝）
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付类型（wechat: 微信, alipay: 支付宝）
     *
     * @param payType 支付类型（wechat: 微信, alipay: 支付宝）
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取收货地址
     *
     * @return address_id - 收货地址
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 设置收货地址
     *
     * @param addressId 收货地址
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取发货时间
     *
     * @return deliver_time - 发货时间
     */
    public Date getDeliverTime() {
        return deliverTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliverTime 发货时间
     */
    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * 获取交易完成时间
     *
     * @return closing_time - 交易完成时间
     */
    public Date getClosingTime() {
        return closingTime;
    }

    /**
     * 设置交易完成时间
     *
     * @param closingTime 交易完成时间
     */
    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    /**
     * 获取总金额
     *
     * @return total_money - 总金额
     */
    public String getTotalMoney() {
        return totalMoney;
    }

    /**
     * 设置总金额
     *
     * @param totalMoney 总金额
     */
    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 获取运费
     *
     * @return shipment_money - 运费
     */
    public String getShipmentMoney() {
        return shipmentMoney;
    }

    /**
     * 设置运费
     *
     * @param shipmentMoney 运费
     */
    public void setShipmentMoney(String shipmentMoney) {
        this.shipmentMoney = shipmentMoney;
    }

    /**
     * 获取订单状态（）
     *
     * @return status - 订单状态（）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置订单状态（）
     *
     * @param status 订单状态（）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取物流信息
     *
     * @return logistics_information - 物流信息
     */
    public String getLogisticsInformation() {
        return logisticsInformation;
    }

    /**
     * 设置物流信息
     *
     * @param logisticsInformation 物流信息
     */
    public void setLogisticsInformation(String logisticsInformation) {
        this.logisticsInformation = logisticsInformation;
    }
}